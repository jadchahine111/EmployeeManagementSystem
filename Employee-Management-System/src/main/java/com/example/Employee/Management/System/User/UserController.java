package com.example.Employee.Management.System.User;

import com.example.Employee.Management.System.User.UserExceptions.EmailAlreadyTakenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User createdUser = userService.addUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path="/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
        User user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(path="{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="{id}")
    public ResponseEntity<User> updateUserInfo(@PathVariable("id") Long id,
                                               @RequestParam(required = false) String username,
                                               @RequestParam(required = false) String email,
                                               @RequestParam(required = false) String phoneNumber,
                                               @RequestParam(required = false) String password,
                                               @RequestParam(required = false) LocalDate dateOfBirth) {
        userService.updateUser(id, username, email, phoneNumber, password, dateOfBirth);
        User updatedUser = userService.getUserById(id); // Fetch updated user
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}

