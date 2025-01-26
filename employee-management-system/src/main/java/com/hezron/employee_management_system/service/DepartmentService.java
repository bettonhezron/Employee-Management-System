package com.hezron.employee_management_system.service;


import com.hezron.employee_management_system.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department createDepartment(Department department);

    Department updateDepartment(Long id, Department updatedDepartment);

    void deleteDepartment(Long id);
}