package com.example.Employee.Management.System.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    @Query("select e from Employee e where e.user_id = ?1")
    Optional<Employee> findByUserID(Long id);


    boolean existsByEmail(String email);
}
