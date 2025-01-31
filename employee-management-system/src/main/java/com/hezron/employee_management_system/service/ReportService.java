package com.hezron.employee_management_system.service;

import com.hezron.employee_management_system.model.CustomReportRequest;
import com.hezron.employee_management_system.model.HeadcountReport;
import com.hezron.employee_management_system.model.PerformanceReport;
import com.hezron.employee_management_system.model.SalaryReport;

import java.util.List;

public interface ReportService {
    List<HeadcountReport> generateHeadcountReport();
    List<SalaryReport> generateSalaryReport();
    List<PerformanceReport> generatePerformanceReport();
    Object generateCustomReport(CustomReportRequest request);
}
