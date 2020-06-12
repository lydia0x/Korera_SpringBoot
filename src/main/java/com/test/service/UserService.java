package com.test.service;

import com.test.domain.User;
import com.test.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String createddate = dtf.format(now);
        user.setCreatedate(createddate);
        return userRepo.save(user);
    }

    public List<User> users(){
        return userRepo.findAll();
    }

    public User findUser(String username) {
        return userRepo.findByUsername(username);
    }
}
