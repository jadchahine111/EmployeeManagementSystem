package com.example.Employee.Management.System.User;

import com.example.Employee.Management.System.Employee.Employee;
import com.example.Employee.Management.System.User.UserExceptions.EmailAlreadyTakenException;
import com.example.Employee.Management.System.User.UserExceptions.UserNotFoundException;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    // GET ALL STUDENTS
    public List<User> getUsers() {
        //Returns Hello World in JSON!
        return userRepository.findAll(); // returns a list of students!!
    }



    // GET user by email
    public User getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with email: '" + email + "' not found");
        }
        return userOptional.get(); // Return the user object from Optional

    }




    // GET user by ID
    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("ID " + id+ " not found.");
        }
        return userOptional.get(); // Return the user object from Optional

    }


    public User addUser(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new EmailAlreadyTakenException("Email is Taken. Please choose another email");
        }
        return userRepository.save(user);
    }



    // DELETE a user
    @Transactional
    public void deleteUserById(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new UserNotFoundException("ID " + id + " not found.");
        }
        userRepository.deleteById(id);
    }

    // PUT a user by ID
    @Transactional
    public void updateUser(Long id, String username, String email, String phoneNumber, String password, LocalDate dateOfBirth) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with Id " + id + " does not exist"));

        if (username != null && !username.isBlank() && !username.equals(user.getUsername())) {
            user.setUsername(username);
        }

        if (email != null && !email.isBlank() && !email.equals(user.getEmail())) {
            // Optional: Add email format validation if not already in place
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isPresent()) {
                throw new EmailAlreadyTakenException("Email is Taken. Please choose another email");
            }
            user.setEmail(email);
        }

        if (phoneNumber != null && !phoneNumber.isBlank() && !phoneNumber.equals(user.getPhoneNumber())) {
            user.setPhoneNumber(phoneNumber);
        }

        if (password != null && !password.isBlank() && !password.equals(user.getPassword())) {
            user.setPassword(password);
        }

        if (dateOfBirth != null && !dateOfBirth.equals(user.getDateOfBirth())) {
            if (dateOfBirth.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Date of birth must be in the past");
            }
            user.setDateOfBirth(dateOfBirth);
        }


        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }



}
