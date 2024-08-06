package com.example.Employee.Management.System.Permission;

import com.example.Employee.Management.System.Job.Job;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @SequenceGenerator(
            name = "permission_sequence",
            sequenceName = "permission_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "permission_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @NotBlank(message = "Type is mandatory")
    @Size(max = 20, message = "Permission Type should not exceed 20 characters")
    @Column(
            name="type",
            nullable = false,
            columnDefinition = "TEXT"

    )
    private String type;

    @Column(
            name="description",
            columnDefinition = "TEXT"
    )
    private String description;

    @OneToMany(mappedBy = "permission")
    private List<Job> jobs = new ArrayList<Job>();

    public Permission(Long id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public Permission(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public Permission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Type is mandatory") String getType() {
        return type;
    }

    public void setType(@NotBlank(message = "Type is mandatory") String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", jobs='" + jobs + '\'' +

                '}';
    }
}
