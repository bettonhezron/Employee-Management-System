package com.hezron.employee_management_system.service;


import com.hezron.employee_management_system.model.Department;
import com.hezron.employee_management_system.model.Employee;
import com.hezron.employee_management_system.model.Report;
import com.hezron.employee_management_system.repository.EmployeeRepository;
import com.hezron.employee_management_system.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService{

    private final EmployeeRepository employeeRepository;

    private final ReportRepository reportRepository;



    public ReportServiceImpl(EmployeeRepository employeeRepository, ReportRepository reportRepository) {
        this.employeeRepository = employeeRepository;
        this.reportRepository = reportRepository;
    }


    @Override
    public Map<String, Object> generatedHeadCountReport() {
        Map<String, Object> report = new HashMap<>();
        List<Employee> employees = employeeRepository.findAll();

        // Group by department
        Map<Department, Long> departmentCounts = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

        report.put("departmentCounts", departmentCounts);
        report.put("totalEmployees", employees.size());

        return report;
    }

    @Override
    public Map<String, Object> generateSalaryReport() {
        Map<String, Object> report = new HashMap<>();
        List<Employee> employees = employeeRepository.findAll();

        // Calculate salary statistics per department
        Map<Department, DoubleSummaryStatistics> salaryStats = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.summarizingDouble(Employee::getSalary)));

        report.put("salaryStatistics", salaryStats);
        return report;
    }

    @Override
    public Map<String, Object> generatePerfomanceReport() {
        return new HashMap<>();
    }

    @Override
    public Report generateCustomReport(Report reportRequest) {
        List<Employee> employees = employeeRepository.findAll();

        // Apply filters
        if (reportRequest.getDepartment() != null) {
            employees = employees.stream()
                    .filter(e -> e.getDepartment().getName().equals(reportRequest.getDepartment()))
                    .collect(Collectors.toList());
        }

        if (reportRequest.isNewHiresOnly()) {
            employees = employees.stream()
                    .filter(e -> e.getHireDate().isAfter(reportRequest.getStartDate()))
                    .collect(Collectors.toList());
        }

        // Store results
        reportRequest.setReportData(generateReportData(employees, reportRequest.getReportType()));
        return reportRepository.save(reportRequest);
    }

    private String generateReportData(List<Employee> employees, String reportType) {
        // Implement report generation logic based on type
        return "reports";
    }

}
