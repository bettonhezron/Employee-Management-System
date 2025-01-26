package com.hezron.employee_management_system.dto;

import java.util.List;


public class CustomReportDTO {
    private String reportType;
    private List<Object>  reportData;

    //Constructors
    public CustomReportDTO(String reportType, List<Object> reportData) {
        this.reportType = reportType;
        this.reportData = reportData;
    }

    // Getters and Setters

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public List<Object> getReportData() {
        return reportData;
    }

    public void setReportData(List<Object> reportData) {
        this.reportData = reportData;
    }
}
