package com.test.controller;


import com.test.domain.Project;
import com.test.domain.Resource;
import com.test.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/")
public class ResourceController {
    @Autowired
    private ResourceService service;


    @RequestMapping(value = "/getResource", method = RequestMethod.GET)
    public ResponseEntity<?> getResource() {
        List<Resource> resources = service.getResource();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @RequestMapping(value = "/addColumn", method = RequestMethod.POST)
    public ResponseEntity<?> addColumn(@RequestBody Resource res){
        List<Resource> resource = service.addResourceColumnn(res);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/addRow", method = RequestMethod.POST)
    public ResponseEntity<?> addRow(@RequestBody Resource res){
        List<Resource> resource = service.addResourceRow(res);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/openCSV", method = RequestMethod.POST)
    public ResponseEntity<?> importResources(@RequestBody String csvFile){
        List<Resource> resources = service.importResources(csvFile);
        return new ResponseEntity<>(resources, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/allProject", method = RequestMethod.GET)
    public ResponseEntity<?> allProject(){
        List<Project> projects = service.allProject();
        return new ResponseEntity<>(projects, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getProject/{projectId}", method = RequestMethod.GET)
    public ResponseEntity<?> getProject(@PathVariable String projectId){
        List<Project> projects = service.getProject(projectId);
        return new ResponseEntity<>(projects, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/addField/{projectId}", method = RequestMethod.POST)
    public ResponseEntity<?> addField(@PathVariable String projectId,@RequestBody Resource res){
        List<Project> projects = service.addField(projectId, res);
        return new ResponseEntity<>(projects, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/selectProject/{projectId}", method = RequestMethod.POST)
    public ResponseEntity<?> selectProject(@PathVariable String projectId,@RequestBody Resource res){
        List<Project> projects = service.selectProject(projectId, res);
        return new ResponseEntity<>(projects, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/updateProject/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProject(@PathVariable int id,
                                          @RequestBody Project updateInfo){
        Project updateProject = service.updateProject(id, updateInfo);
        return new ResponseEntity<>(updateProject, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateResource/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateResource(@PathVariable int id,
                                           @RequestBody Resource updateInfo){
        Resource updateResource = service.updateResource(id, updateInfo);
        return new ResponseEntity<>(updateResource, HttpStatus.OK);
    }

}
