package com.hezron.employee_management_system.controller;

import com.hezron.employee_management_system.model.Employee;
import com.hezron.employee_management_system.service.DepartmentService;
import com.hezron.employee_management_system.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
@Controller
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        try {
            Employee employee = new Employee();
            employee.setHireDate(LocalDate.now());
            model.addAttribute("employee", employee);
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "new_employee";
        } catch (Exception e) {
            logger.error("Error preparing new employee form: ", e);
            model.addAttribute("errorMessage", "Error loading form: " + e.getMessage());
            return "redirect:/employees";
        }
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        try {
            // Basic validation
            if (employee.getFirstName() == null || employee.getFirstName().trim().isEmpty()) {
                model.addAttribute("errorMessage", "First name cannot be empty");
                model.addAttribute("departments", departmentService.getAllDepartments());
                return "new_employee";
            }

            employeeService.saveEmployee(employee);
            redirectAttributes.addFlashAttribute("successMessage", "Employee saved successfully!");
            return "redirect:/employees";

        } catch (Exception e) {
            logger.error("Error saving employee: ", e);
            model.addAttribute("errorMessage", "Error saving employee: " + e.getMessage());
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "new_employee";
        }
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable Long id,
                                    Model model,
                                    RedirectAttributes redirectAttributes) {
        try {
            Employee employee = employeeService.getEmployeeById(id);


            logger.info("Hire Date from DB: " + employee.getHireDate());

            model.addAttribute("employee", employee);
            model.addAttribute("departments", departmentService.getAllDepartments());

            // Add formatted date as a separate model attribute
            model.addAttribute("formattedHireDate",
                    employee.getHireDate().toString());

            return "update_employee";
        } catch (Exception e) {
            logger.error("Error fetching employee for update: ", e);
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error fetching employee details: " + e.getMessage());
            return "redirect:/employees";
        }
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute("employee") Employee employee,
                                 RedirectAttributes redirectAttributes) {
        try {
            // Debug log
            logger.info("Received hire date: " + employee.getHireDate());

            employeeService.updateEmployee(employee);
            redirectAttributes.addFlashAttribute("successMessage", "Employee updated successfully!");
            return "redirect:/employees";
        } catch (Exception e) {
            logger.error("Error updating employee: ", e);
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error updating employee: " + e.getMessage());
            return "redirect:/employees";
        }
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable Long id,
                                 RedirectAttributes redirectAttributes) {
        try {
            employeeService.deleteEmployeeById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Employee deleted successfully!");
        } catch (Exception e) {
            logger.error("Error deleting employee: ", e);
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error deleting employee: " + e.getMessage());
        }
        return "redirect:/employees";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam(value = "sortField", defaultValue = "firstName") String sortField,
                                @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
                                Model model) {
        try {
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

            return "employees";
        } catch (Exception e) {
            logger.error("Error in pagination: ", e);
            model.addAttribute("errorMessage", "Error loading employees: " + e.getMessage());
            return "employees";
        }
    }
}