package com.example.Employee.Management.System.Salary;


import com.example.Employee.Management.System.Employee.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
        name = "salary",
        uniqueConstraints = {
                @UniqueConstraint(name = "employee_id_unique", columnNames = "employee_id")
        }
)
public class Salary {

    @Id
    @SequenceGenerator(
            name = "salary_sequence",
            sequenceName = "salary_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "salary_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;


    @Column(name = "amount")
    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Amount must be greater than or equal to 0")
    @DecimalMax(value = "10000.0", inclusive = true, message = "Amount must be less than or equal to 10000")
    private double amount;


    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    private Employee employee;


    public Salary(Long id, double amount, Employee employee) {
        this.id = id;
        this.amount = amount;
        this.employee = employee;
    }

    public Salary(double amount, Employee employee) {
        this.amount = amount;
        this.employee = employee;
    }

    public Salary() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Amount must be greater than or equal to 0")
    @DecimalMax(value = "10000.0", inclusive = true, message = "Amount must be less than or equal to 10000")
    public double getAmount() {
        return amount;
    }

    public void setAmount(@NotNull(message = "Amount cannot be null") @DecimalMin(value = "0.0", inclusive = true, message = "Amount must be greater than or equal to 0") @DecimalMax(value = "10000.0", inclusive = true, message = "Amount must be less than or equal to 10000") double amount) {
        this.amount = amount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", amount=" + amount +
                ", employee=" + employee +
                '}';
    }
}
