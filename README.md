# EmployeeManagementSystem
Employee Managament System using Spring MVC

## ER Diagram (Chen Notation)
![Alt text](EmployeeManagementSystemERD.png)

## Features
- **Models and Relations**: Defined entities and their relationships using JPA annotations and Hibernate.
- **Spring Data JPA**: Utilized Spring Data JPA for repository management and database interactions.
- **Spring Security 6.3**: Implemented authentication, authorization, and JWT for securing the application.
- **MockMVC and JUnit**: Used MockMVC for testing the REST controllers and JUnit for unit testing.
- **Role-Based Access Control (RBAC)**: Implemented RBAC to manage access within the system. 
  - **HR Employee**: Access to personal and payroll data for all employees.
  - **IT Employee**: Access to system settings and user accounts.
  - **Finance Employee**: Access to salary and compensation data.
