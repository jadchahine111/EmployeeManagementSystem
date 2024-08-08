package com.example.Employee.Management.System.User;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Ensure the password meets the complexity requirements
            String validPassword = "Str0ngP@ssw0rd"; // Must meet the pattern defined

            // Create a User object with encoded password
            User user = new User(
                    "jadchahine2",
                    "jadalichahine@gmail.com",
                    "1234567890", // phoneNumber
                    passwordEncoder.encode(validPassword), // encoded password
                    LocalDate.of(2003, Month.APRIL, 2) // dateOfBirth
            );

            // Save the User to the repository
            userRepository.save(user);
        };
    }
}
