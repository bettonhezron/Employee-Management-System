package com.hezron.employee_management_system.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String reportType;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    private String department;

    @Column
    private boolean newHiresOnly;

    @Column
    private boolean topPerfomerOnly;

    @Column
    private String reportData;

   // Constructors
    public Report(Long id, String reportType, LocalDate startDate, LocalDate endDate, boolean newHiresOnly, boolean topPerfomerOnly, String reportData) {
        this.id = id;
        this.reportType = reportType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.newHiresOnly = newHiresOnly;
        this.topPerfomerOnly = topPerfomerOnly;
        this.reportData = reportData;
    }

    //Getters and Setters
    public String getReportType() {
        return reportType;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public boolean isNewHiresOnly() {
        return newHiresOnly;
    }

    public void setNewHiresOnly(boolean newHiresOnly) {
        this.newHiresOnly = newHiresOnly;
    }

    public boolean isTopPerfomerOnly() {
        return topPerfomerOnly;
    }

    public void setTopPerfomerOnly(boolean topPerfomerOnly) {
        this.topPerfomerOnly = topPerfomerOnly;
    }

    public String getReportData() {
        return reportData;
    }

    public void setReportData(String reportData) {
        this.reportData = reportData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }
}