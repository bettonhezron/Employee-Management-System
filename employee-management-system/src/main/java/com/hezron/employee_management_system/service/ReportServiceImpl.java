package com.hezron.employee_management_system.service;


import com.hezron.employee_management_system.dto.HeadcountReport;
import com.hezron.employee_management_system.model.Department;
import com.hezron.employee_management_system.model.Employee;

import com.hezron.employee_management_system.model.Report;
import com.hezron.employee_management_system.repository.DepartmentRepository;
import com.hezron.employee_management_system.repository.EmployeeRepository;
import com.hezron.employee_management_system.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService{

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    private final ReportRepository reportRepository;

    public ReportServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, ReportRepository reportRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.reportRepository = reportRepository;
    }

    public HeadcountReport generateHeadcountReport() {
        HeadcountReport report = new HeadcountReport();

        // Get all departments and employees
        List<Department> departments = departmentRepository.findAll();
        List<Employee> employees = employeeRepository.findAll();

        // Calculate department counts
        Map<String, Integer> departmentCounts = new LinkedHashMap<>();
        for (Department dept : departments) {
            int count = (int) employees.stream()
                    .filter(e -> e.getDepartment().getId().equals(dept.getId()))
                    .count();
            departmentCounts.put(dept.getName(), count);
        }

        // Set the report data
        report.setDepartmentCounts(departmentCounts);
        report.setTotalEmployees(employees.size());

        // Find largest department
        String largest = departmentCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
        report.setLargestDepartment(largest);

        // Find smallest department
        String smallest = departmentCounts.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
        report.setSmallestDepartment(smallest);

        // Find newest department (assuming department ID is auto-incremented)
        Department newest = departments.stream()
                .max(Comparator.comparing(Department::getId))
                .orElse(null);
        report.setNewestDepartment(newest != null ? newest.getName() : "N/A");

        return report;
    }



}
