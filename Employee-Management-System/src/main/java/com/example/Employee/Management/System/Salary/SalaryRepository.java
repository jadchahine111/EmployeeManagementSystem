package com.example.Employee.Management.System.Salary;

import com.example.Employee.Management.System.Employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalaryRepository extends JpaRepository<Salary, Long> {

    boolean existsByEmployee(Employee employee);

}
