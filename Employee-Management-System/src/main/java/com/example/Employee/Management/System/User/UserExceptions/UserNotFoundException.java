package com.example.Employee.Management.System.User.UserExceptions;

public class UserNotFoundException extends RuntimeException {




    public UserNotFoundException() {
        super();
    }


    public UserNotFoundException(String message) {
        super(message);
    }

}
