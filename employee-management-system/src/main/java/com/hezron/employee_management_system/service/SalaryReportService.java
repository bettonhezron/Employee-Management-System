package com.hezron.employee_management_system.service;

import com.hezron.employee_management_system.dto.SalaryReport;
import com.hezron.employee_management_system.model.Department;
import com.hezron.employee_management_system.model.Employee;
import com.hezron.employee_management_system.repository.DepartmentRepository;
import com.hezron.employee_management_system.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SalaryReportService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public SalaryReportService(DepartmentRepository departmentRepository,
                               EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public SalaryReport generateSalaryReport() {
        List<Employee> employees = employeeRepository.findAll();
        List<Department> departments = departmentRepository.findAll();

        SalaryReport report = new SalaryReport();

        // Compute salary details by department
        Map<String, SalaryReport.SalaryDetails> departmentSalaryDetails = new HashMap<>();

        for (Department dept : departments) {
            List<Employee> deptEmployees = employees.stream()
                    .filter(e -> e.getDepartment().getId().equals(dept.getId()))
                    .toList();

            if (!deptEmployees.isEmpty()) {
                double avgSalary = deptEmployees.stream()
                        .mapToDouble(Employee::getSalary)
                        .average()
                        .orElse(0.0);

                double minSalary = deptEmployees.stream()
                        .mapToDouble(Employee::getSalary)
                        .min()
                        .orElse(0.0);

                double maxSalary = deptEmployees.stream()
                        .mapToDouble(Employee::getSalary)
                        .max()
                        .orElse(0.0);

                double totalDeptSalary = deptEmployees.stream()
                        .mapToDouble(Employee::getSalary)
                        .sum();

                departmentSalaryDetails.put(dept.getName(),
                        new SalaryReport.SalaryDetails(avgSalary, minSalary, maxSalary, totalDeptSalary));
            }
        }

        // Overall payroll calculations
        double totalPayroll = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();

        double overallAverageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);

        // Find highest and lowest paid departments
        String highestPaidDept = departmentSalaryDetails.entrySet().stream()
                .max(Comparator.comparing(entry -> entry.getValue().getAverageSalary()))
                .map(Map.Entry::getKey)
                .orElse("N/A");

        String lowestPaidDept = departmentSalaryDetails.entrySet().stream()
                .min(Comparator.comparing(entry -> entry.getValue().getAverageSalary()))
                .map(Map.Entry::getKey)
                .orElse("N/A");

        // Set report details
        report.setDepartmentSalaryDetails(departmentSalaryDetails);
        report.setOverallAverageSalary(overallAverageSalary);
        report.setTotalPayroll(totalPayroll);
        report.setHighestPaidDepartment(highestPaidDept);
        report.setLowestPaidDepartment(lowestPaidDept);

        return report;
    }
}
