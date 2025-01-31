package com.hezron.employee_management_system.model;

public class SalaryReport {
    private String department;
    private double averageSalary;
    private double minimumSalary;
    private double maximumSalary;
    private int employeeCount;

    //Constructor

    public SalaryReport(String department, double averageSalary, double minimumSalary, double maximumSalary, int employeeCount) {
        this.department = department;
        this.averageSalary = averageSalary;
        this.minimumSalary = minimumSalary;
        this.maximumSalary = maximumSalary;
        this.employeeCount = employeeCount;
    }

    //Getters and setters

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }

    public double getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(double minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    public double getMaximumSalary() {
        return maximumSalary;
    }

    public void setMaximumSalary(double maximumSalary) {
        this.maximumSalary = maximumSalary;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }
}
