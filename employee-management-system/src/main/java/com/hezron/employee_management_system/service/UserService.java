package com.hezron.employee_management_system.service;

import com.hezron.employee_management_system.dto.UserRegistrationDto;
import com.hezron.employee_management_system.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User registerNewUser(UserRegistrationDto registrationDto);
    User findByEmail(String email);
    void updateUser(User user);
    void changePassword(User user, String newPassword);
}