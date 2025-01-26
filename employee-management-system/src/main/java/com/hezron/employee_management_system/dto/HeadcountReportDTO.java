package com.hezron.employee_management_system.dto;

public class HeadcountReportDTO {
    private String department;
    private long employeeCount;
    private double percentageOfTotal;

    //Constructors
    public HeadcountReportDTO(String department, long employeeCount, double percentageOfTotal) {
        this.department = department;
        this.employeeCount = employeeCount;
        this.percentageOfTotal = percentageOfTotal;
    }

      // Getters and Setters
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public long getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(long employeeCount) {
        this.employeeCount = employeeCount;
    }

    public double getPercentageOfTotal() {
        return percentageOfTotal;
    }

    public void setPercentageOfTotal(double percentageOfTotal) {
        this.percentageOfTotal = percentageOfTotal;
    }
}
