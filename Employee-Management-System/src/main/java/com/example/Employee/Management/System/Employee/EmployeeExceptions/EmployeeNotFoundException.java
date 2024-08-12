package com.example.Employee.Management.System.Employee.EmployeeExceptions;

public class EmployeeNotFoundException extends RuntimeException{

    // Default constructor
    public EmployeeNotFoundException() {
        super();
    }

    // Constructor that accepts a message
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
