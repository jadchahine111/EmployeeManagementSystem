package com.example.Employee.Management.System.Employee;

import com.example.Employee.Management.System.Holiday.Holiday;
import com.example.Employee.Management.System.User.User;
import com.example.Employee.Management.System.Salary.Salary;
import com.example.Employee.Management.System.Job.Job;


import com.example.Employee.Management.System.Department.Department;

import com.example.Employee.Management.System.Abstract.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "employee",
        uniqueConstraints = {
                @UniqueConstraint(name = "employee_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "employee_userId_unique", columnNames = "userId")
        }
)
public class Employee extends Person {

    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "firstName",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "First name is mandatory")
    @Size(max = 50, message = "First name should not exceed 50 characters")
    private String firstName;

    @Column(
            name = "lastName",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "Last name is mandatory")
    @Size(max = 50, message = "Last name should not exceed 50 characters")
    private String lastName;


    @Column(name = "hire_date", nullable = false)
    @NotNull(message = "Hire date is mandatory")
    private LocalDateTime hireDate;

    @Embedded
    private Address address;


    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Salary salary;


    @OneToOne
    @JoinColumn(name = "userId", unique = true, nullable = false)
    @NotNull(message = "User is mandatory")
    private User user;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    public List<Holiday> getHolidays() {
        return holidays;
    }


    @ManyToMany(mappedBy = "employees")
    private List<Holiday> holidays = new ArrayList<>();

    public Employee(Job job, Salary salary, String email, String phoneNumber, Long id, String firstName, String lastName, LocalDateTime hireDate, Address address, Department department, User user) {
        super(email, phoneNumber);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.address = address;
        this.department = department;
        this.user = user;
        this.salary = salary;
        this.job = job;
    }

    public Employee(Job job, Salary salary, String email, String phoneNumber, String firstName, String lastName, LocalDateTime hireDate, Address address, Department department, User user) {
        super(email, phoneNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.address = address;
        this.department = department;
        this.user = user;
        this.salary = salary;
        this.job = job;


    }

    public Employee() {
        super(); // Call the no-argument constructor of Person
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "First name is mandatory") @Size(max = 50, message = "First name should not exceed 50 characters") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "First name is mandatory") @Size(max = 50, message = "First name should not exceed 50 characters") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "Last name is mandatory") @Size(max = 50, message = "Last name should not exceed 50 characters") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Last name is mandatory") @Size(max = 50, message = "Last name should not exceed 50 characters") String lastName) {
        this.lastName = lastName;
    }

    public @NotNull(message = "Hire date is mandatory") LocalDateTime getHireDate() {
        return hireDate;
    }

    public void setHireDate(@NotNull(message = "Hire date is mandatory") LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public @NotNull(message = "User is mandatory") User getUser() {
        return user;
    }

    public void setUser(@NotNull(message = "User is mandatory") User user) {
        this.user = user;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hireDate=" + hireDate +
                ", address=" + address +
                ", salary=" + salary +
                ", user=" + user +
                ", department=" + department +
                ", job=" + job +
                ", holidays=" + holidays +
                '}';
    }
}
