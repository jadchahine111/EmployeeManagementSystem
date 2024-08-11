package com.example.Employee.Management.System.Job;

import com.example.Employee.Management.System.Permission.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {

    Optional<Job> findByName(String name);

    boolean existsByName(String name);

}
