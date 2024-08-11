package com.example.Employee.Management.System.Holiday;


import com.example.Employee.Management.System.Employee.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "holiday")
public class Holiday {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", updatable = false)
        private Long id;

        @NotBlank(message = "Name is mandatory")
        @Column(
                name = "name",
                nullable = false,
                columnDefinition = "TEXT"
        )
        private String name;

        @Column(
                name = "start_date",
                nullable = false
        )
        @NotNull(message = "Start date is mandatory")
        private LocalDate startDate;

        @Column(
                name = "end_date",
                nullable = false
        )
        @NotNull(message = "End date is mandatory")
        private LocalDate endDate;

        @ManyToMany
        @JoinTable(
                name = "employee_holiday",
                joinColumns = @JoinColumn(name = "holiday_id"),
                inverseJoinColumns = @JoinColumn(name = "employee_id")
        )
        private List<Employee> employees = new ArrayList<>();

        public Holiday(Long id, String name, LocalDate startDate, LocalDate endDate) {
                this.id = id;
                this.name = name;
                this.startDate = startDate;
                this.endDate = endDate;
        }

        public Holiday(String name, LocalDate startDate, LocalDate endDate) {
                this.name = name;
                this.startDate = startDate;
                this.endDate = endDate;
        }

        public Holiday() {
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public @NotBlank(message = "Name is mandatory") String getName() {
                return name;
        }

        public void setName(@NotBlank(message = "Name is mandatory") String name) {
                this.name = name;
        }

        public @NotNull(message = "Start date is mandatory") LocalDate getStartDate() {
                return startDate;
        }

        public void setStartDate(@NotNull(message = "Start date is mandatory") LocalDate startDate) {
                this.startDate = startDate;
        }

        public @NotNull(message = "End date is mandatory") LocalDate getEndDate() {
                return endDate;
        }

        public void setEndDate(@NotNull(message = "End date is mandatory") LocalDate endDate) {
                this.endDate = endDate;
        }

        public List<Employee> getEmployees() {
                return employees;
        }

        @Override
        public String toString() {
                return "Holiday{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", startDate=" + startDate +
                        ", endDate=" + endDate +
                        ", employees=" + employees +
                        '}';
        }


}
