package com.example.Employee.Management.System.Attendance;

import com.example.Employee.Management.System.User.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance") // Updated table name to "attendance"
public class Attendance {

    @Id
    @SequenceGenerator(
            name = "attendance_sequence", // Updated sequence name
            sequenceName = "attendance_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "attendance_sequence" // Updated sequence name
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "date",
            nullable = false
    )
    @NotNull(message = "Date is mandatory")
    private LocalDateTime date;

    @Column(
            name = "status",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "Status is mandatory")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Attendance() { }

    public Attendance(Long id, LocalDateTime date, String status, User user) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.user = user;
    }

    public Attendance(LocalDateTime date, String status, User user) {
        this.date = date;
        this.status = status;
        this.user = user;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
