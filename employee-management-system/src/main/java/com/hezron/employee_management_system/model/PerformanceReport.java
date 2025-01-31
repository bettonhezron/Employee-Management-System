package com.hezron.employee_management_system.model;

import java.util.List;

public class PerformanceReport {
    private String department;
    private double averagePerformanceScore;
    private int topPerformersCount;
    private List<String> improvementAreas;

    //Constructor

    public PerformanceReport(String department, double averagePerformanceScore, int topPerformersCount, List<String> improvementAreas) {
        this.department = department;
        this.averagePerformanceScore = averagePerformanceScore;
        this.topPerformersCount = topPerformersCount;
        this.improvementAreas = improvementAreas;
    }

    //Getters and Setters

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getAveragePerformanceScore() {
        return averagePerformanceScore;
    }

    public void setAveragePerformanceScore(double averagePerformanceScore) {
        this.averagePerformanceScore = averagePerformanceScore;
    }

    public int getTopPerformersCount() {
        return topPerformersCount;
    }

    public void setTopPerformersCount(int topPerformersCount) {
        this.topPerformersCount = topPerformersCount;
    }

    public List<String> getImprovementAreas() {
        return improvementAreas;
    }

    public void setImprovementAreas(List<String> improvementAreas) {
        this.improvementAreas = improvementAreas;
    }
}
