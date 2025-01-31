package com.hezron.employee_management_system.model;

import java.time.LocalDate;
import java.util.List;

public class CustomReportRequest {
    private String reportType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String department;
    private List<String> filters;

    //constructor
    public CustomReportRequest(String reportType, LocalDate startDate, LocalDate endDate, String department, List<String> filters) {
        this.reportType = reportType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.department = department;
        this.filters = filters;
    }


    //Getters and setters


    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }
}
