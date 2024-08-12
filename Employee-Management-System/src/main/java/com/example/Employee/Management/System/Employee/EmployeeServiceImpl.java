package com.example.Employee.Management.System.Employee;

import com.example.Employee.Management.System.DTO.UserAndEmployeeDTO;
import com.example.Employee.Management.System.Employee.EmployeeExceptions.EmployeeNotFoundException;
import com.example.Employee.Management.System.Employee.EmployeeExceptions.UserOccupiedException;
import com.example.Employee.Management.System.User.User;
import com.example.Employee.Management.System.User.UserExceptions.UserNotFoundException;
import com.example.Employee.Management.System.User.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Employee setUserToEmployee(Long employeeId, Long userId) {
        // Check if user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID: '" + userId + "' not found"));

        // Check if user is already occupied
        if (employeeRepository.findByUser_Id(userId).isPresent()) {
            throw new UserOccupiedException("User with ID: '" + userId + "' is already occupied. Please choose another user ID");
        }

        // Find employee and set user
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID: '" + employeeId + "' not found"));

        employee.setUser(user);
        return employeeRepository.save(employee);
    }

}
