package com.example.Employee.Management.System.Abstract;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@MappedSuperclass
public abstract class Person {

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @NotBlank
    @Pattern(regexp = "\\d{10,15}", message = "Phone number must be between 10 and 15 digits")
    @Column(
            name = "phone_number",
            nullable = false
    )
    private String phoneNumber;

    public Person(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Person() {
    }

    // Getters and Setters

    public @NotBlank @Email @Size(max = 100) String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email @Size(max = 100) String email) {
        this.email = email;
    }

    public @NotBlank @Pattern(regexp = "\\d{10,15}", message = "Phone number must be between 10 and 15 digits") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank @Pattern(regexp = "\\d{10,15}", message = "Phone number must be between 10 and 15 digits") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
