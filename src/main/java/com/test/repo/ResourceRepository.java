package com.test.repo;

import com.test.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
    List<Resource> findAllByResourcename(String resourcename);
    Resource findByid(int id);
}
