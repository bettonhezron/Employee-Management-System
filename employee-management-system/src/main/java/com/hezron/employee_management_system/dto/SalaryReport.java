package com.hezron.employee_management_system.dto;

import java.util.Map;

public class SalaryReport {
    private Map<String, SalaryDetails> departmentSalaryDetails;
    private String highestPaidDepartment;
    private String lowestPaidDepartment;
    private double overallAverageSalary;
    private double totalPayroll;


    // Constructors
    public SalaryReport(Map<String, SalaryDetails> departmentSalaryDetails, String highestPaidDepartment, String lowestPaidDepartment, double overallAverageSalary, double totalPayroll) {
        this.departmentSalaryDetails = departmentSalaryDetails;
        this.highestPaidDepartment = highestPaidDepartment;
        this.lowestPaidDepartment = lowestPaidDepartment;
        this.overallAverageSalary = overallAverageSalary;
        this.totalPayroll = totalPayroll;
    }

    public SalaryReport() {

    }

    public static class SalaryDetails {
        private double averageSalary;
        private double minSalary;
        private double maxSalary;
        private double totalDepartmentSalary;

        public double getAverageSalary() {
            return averageSalary;
        }

        public void setAverageSalary(double averageSalary) {
            this.averageSalary = averageSalary;
        }

        public double getMinSalary() {
            return minSalary;
        }

        public void setMinSalary(double minSalary) {
            this.minSalary = minSalary;
        }

        public double getMaxSalary() {
            return maxSalary;
        }

        public void setMaxSalary(double maxSalary) {
            this.maxSalary = maxSalary;
        }

        public double getTotalDepartmentSalary() {
            return totalDepartmentSalary;
        }

        public void setTotalDepartmentSalary(double totalDepartmentSalary) {
            this.totalDepartmentSalary = totalDepartmentSalary;
        }

        public SalaryDetails(double averageSalary, double minSalary, double maxSalary, double totalDepartmentSalary) {
            this.averageSalary = averageSalary;
            this.minSalary = minSalary;
            this.maxSalary = maxSalary;
            this.totalDepartmentSalary = totalDepartmentSalary;
        }
    }


    public Map<String, SalaryDetails> getDepartmentSalaryDetails() {
        return departmentSalaryDetails;
    }

    public void setDepartmentSalaryDetails(Map<String, SalaryDetails> departmentSalaryDetails) {
        this.departmentSalaryDetails = departmentSalaryDetails;
    }

    public String getHighestPaidDepartment() {
        return highestPaidDepartment;
    }

    public void setHighestPaidDepartment(String highestPaidDepartment) {
        this.highestPaidDepartment = highestPaidDepartment;
    }

    public String getLowestPaidDepartment() {
        return lowestPaidDepartment;
    }

    public void setLowestPaidDepartment(String lowestPaidDepartment) {
        this.lowestPaidDepartment = lowestPaidDepartment;
    }

    public double getOverallAverageSalary() {
        return overallAverageSalary;
    }

    public void setOverallAverageSalary(double overallAverageSalary) {
        this.overallAverageSalary = overallAverageSalary;
    }

    public double getTotalPayroll() {
        return totalPayroll;
    }

    public void setTotalPayroll(double totalPayroll) {
        this.totalPayroll = totalPayroll;
    }
}
