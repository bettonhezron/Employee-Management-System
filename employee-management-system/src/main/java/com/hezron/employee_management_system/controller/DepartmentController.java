package com.hezron.employee_management_system.controller;

import com.hezron.employee_management_system.model.Department;
import com.hezron.employee_management_system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department")
    public String listDepartments(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "departments";
    }

    @GetMapping("/new")
    public String showNewDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "new_department";
    }

    @PostMapping
    public String createDepartment(@ModelAttribute("department") Department department) {
        departmentService.createDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String showEditDepartmentForm(@PathVariable Long id, Model model) {
        Department department = departmentService.getAllDepartments()
                .stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("department", department);
        return "edit-department";
    }

    @PostMapping("/{id}")
    public String updateDepartment(@PathVariable Long id, @ModelAttribute("department") Department updatedDepartment) {
        departmentService.updateDepartment(id, updatedDepartment);
        return "redirect:/departments";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
}