package com.example.Employee.Management.System.Login;

public class JwtResponse {
    private String token;
    private String message;


    public JwtResponse(String token, String message) {
        this.token = token;
        this.message = message;

    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setToken(String token) {
        this.token = token;
    }
}