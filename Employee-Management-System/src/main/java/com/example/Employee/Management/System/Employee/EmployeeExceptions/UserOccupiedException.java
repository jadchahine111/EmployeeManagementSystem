package com.example.Employee.Management.System.Employee.EmployeeExceptions;

public class UserOccupiedException extends RuntimeException{

    // Default constructor
    public UserOccupiedException() {
        super();
    }

    // Constructor that accepts a message
    public UserOccupiedException(String message) {
        super(message);
    }
}
