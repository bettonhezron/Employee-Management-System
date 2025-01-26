package com.hezron.employee_management_system.controller;

import com.hezron.employee_management_system.model.Employee;
import com.hezron.employee_management_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        try {
            // Add some basic validation
            if (employee.getFirstName() == null || employee.getFirstName().isEmpty()) {
                model.addAttribute("errorMessage", "First name cannot be empty");
                return "new_employee";
            }

            employeeService.saveEmployee(employee);
            redirectAttributes.addFlashAttribute("successMessage", "Employee created successfully!");
            return "redirect:/";
        } catch (Exception e) {
            // Log the full stack trace
            e.printStackTrace();

            // Add error message to model
            model.addAttribute("errorMessage", "Error saving employee: " + e.getMessage());
            return "new_employee";
        }
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute("employee") Employee employee,
                                 RedirectAttributes redirectAttributes) {
        employeeService.updateEmployee(employee);
        redirectAttributes.addFlashAttribute("message", "Employee updated successfully!");
        return "redirect:/";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(
            @PathVariable(value = "pageNo") int pageNo,
            @RequestParam(value = "sortField", defaultValue = "firstName") String sortField,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
            Model model
    ) {
        int pageSize = 5;
        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employee> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listEmployees", listEmployees);

        return "index";
    }
}
