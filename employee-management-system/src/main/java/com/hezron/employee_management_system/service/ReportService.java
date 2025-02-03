package com.hezron.employee_management_system.service;


import com.hezron.employee_management_system.model.Report;

import java.util.Map;


public interface ReportService {
    Map<String, Object> generatedHeadCountReport();
    Map<String, Object>  generateSalaryReport();
    Map<String, Object> generatePerfomanceReport();
    Report generateCustomReport(Report reportRequest);

}