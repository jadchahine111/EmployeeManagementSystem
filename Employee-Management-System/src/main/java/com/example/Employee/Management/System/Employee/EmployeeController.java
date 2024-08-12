package com.example.Employee.Management.System.Employee;

import com.example.Employee.Management.System.DTO.UserAndEmployeeDTO;
import com.example.Employee.Management.System.Employee.EmployeeExceptions.UserOccupiedException;
import com.example.Employee.Management.System.User.UserExceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/assignUserById")
    public ResponseEntity<Employee> setUserToEmployee(@RequestParam Long employeeId, @RequestParam Long userId) {
        Employee updatedEmployee = employeeService.setUserToEmployee(employeeId, userId);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }



}
