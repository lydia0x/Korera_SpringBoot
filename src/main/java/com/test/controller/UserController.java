package com.test.controller;

import com.test.domain.User;
import com.test.security.AuthenticationResponse;
import com.test.security.JwtUtil;
import com.test.security.MyUserDetailsService;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> users() {
        List<User> users = userService.users();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody User user){
        Map<String,String> msg = new HashMap<>();
        HttpStatus httpStatus;
        if (userService.findUser(user.getUsername()) == null) {
            User newUser = userService.addUser(user);
            msg.put("Message","Sign up successful");
            httpStatus = HttpStatus.CREATED;
        }else{
            msg.put("Message","Username already taken");
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(msg, httpStatus);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticateToken(@RequestBody User user) throws Exception{
        String username;
        String createdate;
        String imageurl;
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
            User userInfo = userService.findUser(user.getUsername());
            username = userInfo.getUsername();
            createdate = userInfo.getCreatedate();
            imageurl = userInfo.getImageurl();
        }catch(BadCredentialsException e){
            throw new Exception("Incorrect username or password");
        }
        final UserDetails userDetails = userDetailService.loadUserByUsername(user.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        //return ResponseEntity.ok(new AuthenticationResponse(jwt));
        Map<String,String> map = new HashMap<>();
        map.put("jwt",jwt);
        map.put("username",username);
        map.put("createdate",createdate);
        map.put("imageurl",imageurl);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}
