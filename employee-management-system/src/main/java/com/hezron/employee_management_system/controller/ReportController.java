package com.hezron.employee_management_system.controller;



import com.hezron.employee_management_system.dto.HeadcountReport;
import com.hezron.employee_management_system.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/headcount")
    public String showHeadcountReport(Model model) {
        HeadcountReport report = reportService.generateHeadcountReport();
        model.addAttribute("report", report);
        return "headcount";
    }

    @GetMapping
    public String showReportsPage() {
        return "reports";
    }
}