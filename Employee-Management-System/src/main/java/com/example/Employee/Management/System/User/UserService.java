package com.example.Employee.Management.System.User;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface UserService {

    // GET ALL USERS
    List<User> getUsers();

    // GET user by email
    User getUserByEmail(String email);

    // GET user by ID
    User getUserById(Long id);

    // ADD a new user
    User addUser(User user);

    // DELETE a user
    void deleteUserById(Long id);

    // UPDATE a user by ID
    void updateUser(Long id, String username, String email, String phoneNumber, String password, LocalDate dateOfBirth);
}
