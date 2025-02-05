package com.hezron.employee_management_system.controller;

import com.hezron.employee_management_system.dto.SalaryReport;
import com.hezron.employee_management_system.service.SalaryReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/reports")
public class SalaryReportController {
    private final SalaryReportService salaryReportService;

    public SalaryReportController(SalaryReportService salaryReportService) {
        this.salaryReportService = salaryReportService;
    }

    @GetMapping("/salary")
    public String showSalaryReport(Model model) {
        SalaryReport report = salaryReportService.generateSalaryReport();
        model.addAttribute("report", report);
        return "salary_report";
    }
}
