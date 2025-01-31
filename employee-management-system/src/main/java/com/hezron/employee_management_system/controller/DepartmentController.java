package com.hezron.employee_management_system.controller;

import com.hezron.employee_management_system.model.Department;
import com.hezron.employee_management_system.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class DepartmentController {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public String listDepartments(Model model) {
        try {
            List<Department> departments = departmentService.getAllDepartments();
            model.addAttribute("departments", departments);
            return "departments";
        } catch (Exception e) {
            logger.error("Error fetching departments: ", e);
            model.addAttribute("errorMessage", "Failed to fetch departments");
            return "departments";
        }
    }

    @GetMapping("/departments/new")
    public String showNewDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "new_departments";
    }

    @PostMapping("/departments/save")
    public String saveDepartment(@ModelAttribute Department department,
                                 RedirectAttributes redirectAttributes) {
        try {
            departmentService.saveDepartment(department);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Department saved successfully!");
            return "redirect:/departments";
        } catch (Exception e) {
            logger.error("Error saving department: ", e);
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Failed to save department: " + e.getMessage());
            return "redirect:/departments";
        }
    }

    @GetMapping("/departments/edit/{id}")
    public String showEditDepartmentForm(@PathVariable Long id, Model model,
                                         RedirectAttributes redirectAttributes) {
        try {
            Department department = departmentService.getDepartmentById(id);
            if (department == null) {
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Department not found");
                return "redirect:/departments";
            }
            model.addAttribute("department", department);
            return "edit_department";
        } catch (Exception e) {
            logger.error("Error fetching department: ", e);
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Failed to fetch department");
            return "redirect:/departments";
        }
    }

    @GetMapping("/departments/delete/{id}")
    public String deleteDepartment(@PathVariable Long id,
                                   RedirectAttributes redirectAttributes) {
        try {
            departmentService.deleteDepartment(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Department deleted successfully!");
            return "redirect:/departments";
        } catch (Exception e) {
            logger.error("Error deleting department: ", e);
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Failed to delete department: " + e.getMessage());
            return "redirect:/departments";
        }
    }
}