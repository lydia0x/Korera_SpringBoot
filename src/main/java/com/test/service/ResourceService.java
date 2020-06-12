package com.test.service;



import com.test.domain.Project;
import com.test.domain.Resource;
import com.test.repo.ProjectRepository;
import com.test.repo.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


@Service
public class ResourceService {
    private final ResourceRepository resourceRepository;
    private final ProjectRepository projectRepository;

    public ResourceService(ResourceRepository resourceRepository, ProjectRepository projectRepository) {
        this.resourceRepository = resourceRepository;
        this.projectRepository = projectRepository;
    }

    public void emptyResources(){
        resourceRepository.deleteAll();
    }

    public List<Resource> importResources(String csvFile) {
        emptyResources();

        List<Resource> resources = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            int iteration = 0;
            while ((line = br.readLine()) != null) {
                if (iteration == 0) {
                    iteration++;
                    continue;
                }
                String[] values = line.split(",");
                Resource resource = new Resource();
                resource.setResourcecode(values[0]);
                resource.setResourcename(values[1]);
                resourceRepository.save(resource);
                resources.add(resource);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resources;
    }

    public List<Resource> getResource() {
        return (List<Resource>) resourceRepository.findAll();
    }

    public List<Resource> addResourceColumnn(Resource res) {
        List<Resource> resourceAll = getResource();
        List<Resource> updateResource = new ArrayList<>();
        Set<String> resourceN = new HashSet<String>();
        String columnName = res.getColumnname();

        for (Resource resourceTable : resourceAll) {
//            if(resourceTable.getColumnname() != null){
                while(resourceN.add(resourceTable.getResourcename())) {
                    Resource newResource = new Resource();
                    newResource.setResourcecode(resourceTable.getResourcecode());
                    newResource.setResourcename(resourceTable.getResourcename());
                    newResource.setColumnname(columnName);
                    resourceRepository.save(newResource);
                    updateResource.add(newResource);
                }
//            }
//            else{
//                resourceTable.setColumnname(columnName);
//                resourceRepository.save(resourceTable);
//                return resourceAll;
//            }
        }
        return updateResource;


    }

    //add new row
    public List<Resource> addResourceRow(Resource res) {
        //get all column from resource table
        List<Resource> resources = getResource();
        List<Resource> resourceAdd = new ArrayList<>();
        Set<String> columnN = new HashSet<String>();

        for (Resource rc : resources) {
            columnN.add(rc.getColumnname());
        }
        Iterator it = columnN.iterator();
        while (it.hasNext()) {
            Resource resourceNew = new Resource();
            resourceNew.setResourcename(res.getResourcename());
            resourceNew.setResourcecode(res.getResourcecode());
            resourceNew.setColumnname((String) it.next());
            resourceRepository.save(resourceNew);
            resourceAdd.add(resourceNew);
        }

        return resourceAdd;
    }

    // Display the whole Project Database
    public List<Project> allProject() {
        return (List<Project>) projectRepository.findAll();
    }

    // Display the Project by ProjectID and Create a project with all the resource if the project does not exist
    public List<Project> getProject(String projectId){

        List<Project> allProject = allProject();
        List<Project> selectProject = new ArrayList<>();

        selectProject = projectRepository.findAllByProjectId(projectId);

        //Create a project with all the resource if the project does not exist
//        if(selectProject.size()==0) {
//            List<ResourceColumn> resourcesC = getResourceColumn();
//            for(ResourceColumn resC : resourcesC){
//                Project projectNew = new Project();
//                projectNew.setprojectId(projectId);
//                projectNew.setColumnname(resC.getColumnname());
//                projectNew.setRowdata(resC.getRowdata());
//                projectNew.setResourcecode(resC.getResourcecode());
//                projectNew.setResourcename(resC.getResourcename());
//                projectRepository.save(projectNew);
//                selectProject.add(projectNew);
//
//            }
//        }

        return selectProject;
    }

    public List<Project> selectProject(String projectId, Resource res){
        // get all the project by the given projectId
        List<Project> selectProject = new ArrayList<>();

        String selectName = res.getResourcename();
        List<Resource> resources = resourceRepository.findAllByResourcename(selectName);

        Set<String> resourceN = new HashSet<String>();

        for(Resource resNew : resources){
            while(resourceN.add(resNew.getResourcename())) {
                Project projectNew = new Project();
                projectNew.setprojectId(projectId);
                projectNew.setResourcecode(resNew.getResourcecode());
                projectNew.setResourcename(resNew.getResourcename());
                projectRepository.save(projectNew);
                selectProject.add(projectNew);
            }

        }

        return selectProject;
    }




    public List<Project> addField(String projectId, Resource res){
        // get all the project by the given projectId
        List<Project> allProject = projectRepository.findAllByProjectId(projectId);
        List<Project> selectProject = new ArrayList<>();
        Set<String> resourceN = new HashSet<String>();

        String newField = res.getColumnname();

        for(Project projectTemp : allProject){

            while(resourceN.add(projectTemp.getResourcename())) {
                Project projectNew = new Project();
                projectNew.setprojectId(projectTemp.getprojectId());
                projectNew.setColumnname(projectTemp.getColumnname());
                projectNew.setResourcecode(projectTemp.getResourcecode());
                projectNew.setResourcename(projectTemp.getResourcename());
                projectNew.setfieldType(projectTemp.getfieldType());
                projectNew.setColumnname(newField);
                projectRepository.save(projectNew);
                selectProject.add(projectNew);
            }
        }
       return selectProject;
    }



    public Project updateProject(int id, Project updateInfo){
        Project updateProject = projectRepository.findByid(id);

        // user should not be able to change Resourcename
//        updateProject.setResourcename(updateInfo.getResourcename());

        if(updateInfo.getResourcecode() != null) {
            updateProject.setResourcecode(updateInfo.getResourcecode());
        }
        if(updateInfo.getRowdata() != null) {
            updateProject.setRowdata(updateInfo.getRowdata());
        }
        projectRepository.save(updateProject);
        return updateProject;
    }

    public Resource updateResource(int id, Resource updateInfo){
        Resource updateResource = resourceRepository.findByid(id);

        // user should not be able to change Resourcename
//        updateResource.setResourcename(updateInfo.getResourcename());

        if(updateInfo.getResourcecode() != null) {
            updateResource.setResourcecode(updateInfo.getResourcecode());
        }
        if(updateInfo.getRowdata() != null) {
            updateResource.setRowdata(updateInfo.getRowdata());
        }
        resourceRepository.save(updateResource);
        return updateResource;
    }

}
