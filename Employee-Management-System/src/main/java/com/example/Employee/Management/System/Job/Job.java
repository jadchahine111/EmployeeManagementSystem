package com.example.Employee.Management.System.Job;

import com.example.Employee.Management.System.Permission.Permission;
import com.example.Employee.Management.System.Employee.Employee;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "Name is mandatory")
    @Size(max = 20, message = "Name should not exceed 20 characters")
    private String name;

    @Column(
            name = "description",
            columnDefinition = "TEXT"
    )
    private String description;



    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();


    public Job(Long id, String name, String description, Permission permission) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.permission = permission;
    }

    public Job(String name, String description, Permission permission) {
        this.name = name;
        this.description = description;
        this.permission = permission;
    }

    public Job() {
    }


    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Name is mandatory") @Size(max = 20, message = "Name should not exceed 20 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is mandatory") @Size(max = 20, message = "Name should not exceed 20 characters") String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", employees='" + employees + '\'' +

                '}';
    }
}
