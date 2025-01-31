package com.hezron.employee_management_system.controller;

import com.hezron.employee_management_system.service.DepartmentService;
import com.hezron.employee_management_system.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

@Controller
public class ReportsController {
    private static final Logger logger = LoggerFactory.getLogger(ReportsController.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/reports")
    public String showReportsPage(Model model) {
        try {
            // Add departments list for the dropdown
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "reports";
        } catch (Exception e) {
            logger.error("Error loading reports page: ", e);
            model.addAttribute("errorMessage", "Error loading reports page");
            return "reports";
        }
    }

    @GetMapping("/reports/headcount")
    public String generateHeadcountReport(Model model) {
        try {
            Map<String, Long> departmentHeadcount = employeeService.getEmployeeCountByDepartment();
            model.addAttribute("headcountData", departmentHeadcount);
            model.addAttribute("totalEmployees", departmentHeadcount.values().stream().mapToLong(Long::longValue).sum());
            return "reports/headcount-report";
        } catch (Exception e) {
            logger.error("Error generating headcount report: ", e);
            model.addAttribute("errorMessage", "Error generating headcount report");
            return "reports";
        }
    }

    @GetMapping("/reports/salary")
    public String generateSalaryReport(Model model) {
        try {
            Map<String, DoubleSummaryStatistics> salaryStats = employeeService.getSalaryStatisticsByDepartment();
            model.addAttribute("salaryStats", salaryStats);
            return "reports/salary-report";
        } catch (Exception e) {
            logger.error("Error generating salary report: ", e);
            model.addAttribute("errorMessage", "Error generating salary report");
            return "reports";
        }
    }

    @PostMapping("/reports/custom")
    public String generateCustomReport(@RequestParam String reportType,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                       @RequestParam(required = false) String department,
                                       @RequestParam(required = false) List<String> filters,
                                       Model model) {
        try {
            Map<String, Object> reportData = employeeService.generateCustomReport(
                    reportType, startDate, endDate, department, filters);
            model.addAttribute("reportData", reportData);
            model.addAttribute("reportType", reportType);
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
            return "reports/custom-report";
        } catch (Exception e) {
            logger.error("Error generating custom report: ", e);
            model.addAttribute("errorMessage", "Error generating custom report: " + e.getMessage());
            return "reports";
        }
    }
}