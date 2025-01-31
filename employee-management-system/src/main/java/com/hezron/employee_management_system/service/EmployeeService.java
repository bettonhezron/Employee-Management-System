package com.hezron.employee_management_system.service;

import com.hezron.employee_management_system.dto.CustomReportDTO;
import com.hezron.employee_management_system.dto.HeadcountReportDTO;
import com.hezron.employee_management_system.dto.SalaryDistributionDTO;
import com.hezron.employee_management_system.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;


public interface EmployeeService {

    long countTotalEmployees();

    // Get recent hires (limit by number of employees)
    List<Employee> getRecentHires(int limit);

    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    void deleteEmployeeById(Long id);
    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    void updateEmployee(Employee employee);

    Map<String, Long> getEmployeeCountByDepartment();

    Map<String, DoubleSummaryStatistics> getSalaryStatisticsByDepartment();

    Map<String, Object> generateCustomReport(String reportType, LocalDate startDate, LocalDate endDate, String department, List<String> filters);
}