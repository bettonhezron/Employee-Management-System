package com.hezron.employee_management_system.repository;

import com.hezron.employee_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
            User findByEmail(String email);
}
