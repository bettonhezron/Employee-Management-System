package com.hezron.employee_management_system.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout() {
        // Explicitly clear the security context
        SecurityContextHolder.clearContext();

        // Redirect to login page with logout parameter
        return "redirect:/login?logout";
    }
}