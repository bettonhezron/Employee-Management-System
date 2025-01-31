package com.hezron.employee_management_system.model;

import jakarta.persistence.*;



import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;


    @Column(name = "last_name", nullable = false)
    private String lastName;


    @Column(name = "email", nullable = false, unique = true)
    private String email;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "department_id", nullable = false)


    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Department getDepartment() {
//        return department;
//    }

//    public void setDepartment(Department department) {
//        this.department = department;
//    }
//
//    public LocalDate getHireDate() {
//        return hireDate;
//    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
}
