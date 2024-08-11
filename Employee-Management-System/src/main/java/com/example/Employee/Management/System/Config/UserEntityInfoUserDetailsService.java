package com.example.Employee.Management.System.Config;

import com.example.Employee.Management.System.User.User;
import com.example.Employee.Management.System.User.UserExceptions.UserNotFoundException;
import com.example.Employee.Management.System.User.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityInfoUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserEntityInfoUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(UserEntityInfoUserDetails::new).orElseThrow(() -> new UserNotFoundException("User does not exist"));
    }
}
