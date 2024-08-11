package com.example.Employee.Management.System.CommandLineRunner;

import com.example.Employee.Management.System.Department.Department;
import com.example.Employee.Management.System.Department.DepartmentRepository;
import com.example.Employee.Management.System.Employee.Address;
import com.example.Employee.Management.System.Employee.Employee;
import com.example.Employee.Management.System.Employee.EmployeeRepository;
import com.example.Employee.Management.System.Job.Job;
import com.example.Employee.Management.System.Job.JobRepository;
import com.example.Employee.Management.System.Permission.Permission;
import com.example.Employee.Management.System.Permission.PermissionRepository;
import com.example.Employee.Management.System.Salary.Salary;
import com.example.Employee.Management.System.Salary.SalaryRepository;
import com.example.Employee.Management.System.User.User;
import com.example.Employee.Management.System.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Configuration
public class AppConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository,
                                        SalaryRepository salaryRepository,
                                        JobRepository jobRepository,
                                        DepartmentRepository departmentRepository,
                                        PermissionRepository permissionRepository,
                                        EmployeeRepository employeeRepository,
                                        PasswordEncoder passwordEncoder) {
        return args -> {
            // Permissions
            Permission permission1 = new Permission("READ", "GET requests ONLY");
            Permission permission2 = new Permission("WRITE", "All requests but limited");
            Permission permission3 = new Permission("FULL_ACCESS", "All requests");
            permissionRepository.saveAll(List.of(permission1, permission2, permission3));

            // User
            String validPassword = "Str0ngP@ssw0rd";
            User user = new User(
                    "jadchahine2",
                    "jadalichahine2@gmail.com",
                    "1234567890",
                    passwordEncoder.encode(validPassword),
                    LocalDate.of(2003, Month.APRIL, 2)
            );
            userRepository.save(user);

            // Department
            Department department = new Department("HR", "Human Resources");
            departmentRepository.save(department);

            // Job
            Optional<Permission> fullAccessPermission = permissionRepository.findByType("FULL_ACCESS");
            Permission permission = fullAccessPermission.orElseThrow(() -> new RuntimeException("Permission not found"));
            Job job = new Job("HR", "Human Resources", permission);
            jobRepository.save(job);

            // Employee
            Address address = new Address("dada", "dasdasda", "dasdasd", "dasdasdas");
            LocalDateTime birthDateTime = LocalDateTime.of(2003, Month.APRIL, 2, 0, 0);
            Employee employee = new Employee(
                    job,
                    null, // Temporarily set salary to null
                    user.getEmail(),
                    user.getPhoneNumber(),
                    "Jad",
                    "Chahine",
                    birthDateTime,
                    address,
                    department,
                    user
            );
            employeeRepository.save(employee);

            // Salary
            Salary salary = new Salary(2000.00, employee);
            salaryRepository.save(salary);

            // Update employee with salary
            employee.setSalary(salary);
            employeeRepository.save(employee);
        };
    }
}
