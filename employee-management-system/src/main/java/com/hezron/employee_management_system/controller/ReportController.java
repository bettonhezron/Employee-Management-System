//package com.hezron.employee_management_system.controller;
//
//import com.hezron.employee_management_system.service.EmployeeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
///*
//@Controller
//@RequestMapping("/reports")
//public class ReportController {
//    @Autowired
//    private EmployeeService employeeService;
//
//    @GetMapping
//    public String reportDashboard() {
//        return "reports";
//    }
//
//    @GetMapping("/headcount")
//    public String headcountReport(Model model) {
//        // Fetch headcount data
//        model.addAttribute("departmentCounts", employeeService.getDepartmentCounts());
//        return "reports/headcount";
//    }
//
//    @GetMapping("/salary")
//    public String salaryReport(Model model) {
//        // Fetch salary distribution data
//        model.addAttribute("salaryDistribution", employeeService.getSalaryDistribution());
//        return "reports/salary";
//    }
//
//    @PostMapping("/custom")
//    public String generateCustomReport(
//            @RequestParam(required = false) String reportType,
//            @RequestParam(required = false) String startDate,
//            @RequestParam(required = false) String endDate,
//            @RequestParam(required = false) String department,
//            @RequestParam(required = false) List<String> filters,
//            Model model
//    ) {
//        // Logic to generate custom report based on parameters
//        model.addAttribute("customReportData",
//                employeeService.generateCustomReport(reportType, startDate, endDate, department, filters)
//        );
//        return "reports/custom-report";
//
// */
//
//
//}