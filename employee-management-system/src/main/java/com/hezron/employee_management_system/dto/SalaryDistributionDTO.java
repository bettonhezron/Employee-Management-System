package com.hezron.employee_management_system.dto;

public class SalaryDistributionDTO {
    private String salaryRange;
    private  long employeeCount;
    private double averageSalary;

    // Constructor
    public SalaryDistributionDTO(String salaryRange, long employeeCount, double averageSalary) {
        this.salaryRange = salaryRange;
        this.employeeCount = employeeCount;
        this.averageSalary = averageSalary;
    }

    // Getters and Setters
    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public long getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(long employeeCount) {
        this.employeeCount = employeeCount;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }
}
