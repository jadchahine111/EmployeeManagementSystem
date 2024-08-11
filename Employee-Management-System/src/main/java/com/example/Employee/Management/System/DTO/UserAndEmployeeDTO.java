package com.example.Employee.Management.System.DTO;

import com.example.Employee.Management.System.Employee.Employee;
import com.example.Employee.Management.System.User.User;

public class UserAndEmployeeDTO {

    private User user;
    private Employee employee;

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
