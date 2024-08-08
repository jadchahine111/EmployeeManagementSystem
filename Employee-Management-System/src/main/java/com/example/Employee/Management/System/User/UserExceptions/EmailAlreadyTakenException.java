package com.example.Employee.Management.System.User.UserExceptions;

public class EmailAlreadyTakenException extends RuntimeException {

    // Default constructor
    public EmailAlreadyTakenException() {
        super();
    }

    // Constructor that accepts a message
    public EmailAlreadyTakenException(String message) {
        super(message);
    }


}
