package com.hezron.employee_management_system.dto;



import java.util.Map;

public class HeadcountReport {
    private Map<String, Integer> departmentCounts;
    private int totalEmployees;
    private String newestDepartment;
    private String largestDepartment;
    private String smallestDepartment;

    // Getters and setters
    public Map<String, Integer> getDepartmentCounts() {
        return departmentCounts;
    }

    public void setDepartmentCounts(Map<String, Integer> departmentCounts) {
        this.departmentCounts = departmentCounts;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public String getNewestDepartment() {
        return newestDepartment;
    }

    public void setNewestDepartment(String newestDepartment) {
        this.newestDepartment = newestDepartment;
    }

    public String getLargestDepartment() {
        return largestDepartment;
    }

    public void setLargestDepartment(String largestDepartment) {
        this.largestDepartment = largestDepartment;
    }

    public String getSmallestDepartment() {
        return smallestDepartment;
    }

    public void setSmallestDepartment(String smallestDepartment) {
        this.smallestDepartment = smallestDepartment;
    }
}
