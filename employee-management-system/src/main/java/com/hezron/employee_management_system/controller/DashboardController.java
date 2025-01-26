package com.hezron.employee_management_system.controller;

import com.hezron.employee_management_system.model.Employee;
import com.hezron.employee_management_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // Total employees
        long totalEmployees = employeeService.countTotalEmployees();
        model.addAttribute("totalEmployees", totalEmployees);

        // Recent hires (limit 3)
        List<Employee> recentHires = employeeService.getRecentHires(3);
        model.addAttribute("recentHires", recentHires);

        // Additional dashboard data can be added here as needed
        return "dashboard";
    }
}