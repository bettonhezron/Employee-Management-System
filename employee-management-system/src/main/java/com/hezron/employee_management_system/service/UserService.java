package com.hezron.employee_management_system.service;

import com.hezron.employee_management_system.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);
}
