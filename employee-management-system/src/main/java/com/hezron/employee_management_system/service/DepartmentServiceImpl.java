package com.hezron.employee_management_system.service;


import com.hezron.employee_management_system.model.Department;
import com.hezron.employee_management_system.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department createDepartment(Department department) {
        if (departmentRepository.existsByName(department.getName())) {
            throw new IllegalArgumentException("Department name already exists.");
        }
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Long id, Department updatedDepartment) {
        Optional<Department> existingDepartment = departmentRepository.findById(id);
        if (existingDepartment.isPresent()) {
            Department department = existingDepartment.get();
            department.setName(updatedDepartment.getName());
            return departmentRepository.save(department);
        } else {
            throw new IllegalArgumentException("Department not found.");
        }
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}