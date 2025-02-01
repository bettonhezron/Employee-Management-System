package com.hezron.employee_management_system.repository;

import com.hezron.employee_management_system.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  List<Employee> findTop5ByOrderByHireDateDesc();


}
