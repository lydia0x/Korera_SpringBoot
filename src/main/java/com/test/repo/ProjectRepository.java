package com.test.repo;

import com.test.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {


    List<Project> findAllByProjectId(String projectId);
    Project findByid(int id);
}
