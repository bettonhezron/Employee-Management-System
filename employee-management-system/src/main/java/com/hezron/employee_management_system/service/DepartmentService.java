package com.hezron.employee_management_system.service;

import com.hezron.employee_management_system.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Department saveDepartment(Department department);
    Department getDepartmentById(Long id);
    void deleteDepartment(Long id);
}
