package com.example.Employee.Management.System.User;

import com.example.Employee.Management.System.Abstract.Person;
import com.example.Employee.Management.System.Employee.Employee;
import com.example.Employee.Management.System.Attendance.Attendance;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "email")
        }
)
public class User extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "Username is mandatory")
    @Size(max = 1000)
    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String username;

    @Transient
    private Integer age;

    @NotBlank(message = "Password is mandatory")
    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;



    @Past(message = "Date of birth must be in the past")
    @Column(
            name = "date_of_birth"
    )
    private LocalDate dateOfBirth;

    @Column(
            name = "created_at",
            updatable = false)
    private LocalDateTime createdAt;

     @Column(
            name = "updated_at"
    )
    private LocalDateTime updatedAt;



    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Employee employee;


    @OneToMany(
            mappedBy = "user" ,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Attendance> attendances = new ArrayList<>();



    // Constructors

    public User(Long id, String username, String email, String phoneNumber, String password, LocalDate dateOfBirth, Employee employee) {
        super(email, phoneNumber);
        this.id = id;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.employee = employee;

    }

    public User(String username,  String email, String phoneNumber, String password, LocalDate dateOfBirth, Employee employee) {
        super(email, phoneNumber);
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.employee = employee;
    }

    public User(String username,  String email, String phoneNumber, String password, LocalDate dateOfBirth) {
        super(email, phoneNumber);
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public User() {
        super(); // Call the no-argument constructor of Person
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }



    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


// Getters and Setters



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @Size(max = 1000) String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank @Size(max = 1000) String username) {
        this.username = username;
    }



    public @NotBlank  String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }


    public @Past LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@Past LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }




//toString

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", age=" + age +
                ", attendances=" + attendances +



                '}';
    }
}
