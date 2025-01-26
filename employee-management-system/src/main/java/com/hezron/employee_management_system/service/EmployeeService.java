package com.hezron.employee_management_system.service;

import com.hezron.employee_management_system.dto.CustomReportDTO;
import com.hezron.employee_management_system.dto.HeadcountReportDTO;
import com.hezron.employee_management_system.dto.SalaryDistributionDTO;
import com.hezron.employee_management_system.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

}