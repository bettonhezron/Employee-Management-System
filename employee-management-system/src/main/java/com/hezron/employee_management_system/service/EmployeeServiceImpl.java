package com.hezron.employee_management_system.service;

import com.hezron.employee_management_system.model.Employee;
import com.hezron.employee_management_system.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public long countTotalEmployees() {
        // Returns the total count of employees in the database
        return employeeRepository.count();
    }

    @Override
    public List<Employee> getRecentHires(int limit) {
       // Fetches the top 5 recent hires by hire date
        return employeeRepository.findTop5ByOrderByHireDateDesc();
    }

    @Override
    public List<Employee> getAllEmployees() {
        // Fetches all employees
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void updateEmployee(Employee employee) {
        Employee existingEmployee = getEmployeeById(employee.getId());
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Map<String, Long> getEmployeeCountByDepartment() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getDepartment().getName(),
                        Collectors.counting()
                ));
    }

    public Map<String, DoubleSummaryStatistics> getSalaryStatisticsByDepartment() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getDepartment().getName(),
                        Collectors.summarizingDouble(Employee::getSalary)
                ));
    }

    public Map<String, Object> generateCustomReport(String reportType,
                                                    LocalDate startDate,
                                                    LocalDate endDate,
                                                    String department,
                                                    List<String> filters) {
        Map<String, Object> reportData = new HashMap<>();
        List<Employee> employees = employeeRepository.findAll();

        // Filter by date range
        employees = employees.stream()
                .filter(e -> !e.getHireDate().isBefore(startDate) && !e.getHireDate().isAfter(endDate))
                .collect(Collectors.toList());

        // Filter by department if specified
        if (department != null && !department.isEmpty()) {
            employees = employees.stream()
                    .filter(e -> e.getDepartment().getName().equals(department))
                    .collect(Collectors.toList());
        }

        // Add specific report type calculations
        switch (reportType) {
            case "hiring":
                reportData.put("hiringTrends", calculateHiringTrends(employees));
                break;
            case "retention":
                reportData.put("retentionStats", calculateRetentionStats(employees));
                break;
            case "department":
                reportData.put("departmentStats", calculateDepartmentStats(employees));
                break;
        }

        return reportData;
    }

    private Map<String, Long> calculateHiringTrends(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getHireDate().getMonth().toString(),
                        Collectors.counting()
                ));
    }

    private Map<String, Object> calculateRetentionStats(List<Employee> employees) {
        Map<String, Object> stats = new HashMap<>();
        // Add your retention calculation logic here
        return stats;
    }

    private Map<String, Object> calculateDepartmentStats(List<Employee> employees) {
        Map<String, Object> stats = new HashMap<>();
        // Add your department-specific calculation logic here
        return stats;
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.employeeRepository.findAll(pageable);
    }


}