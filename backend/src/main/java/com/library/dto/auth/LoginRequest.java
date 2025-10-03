package com.library.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    
    @NotBlank(message = "Username (email or student ID) is required")
    private String username; // Can be email or student ID
    
    @NotBlank(message = "Password is required")
    private String password;
    
    private Boolean rememberMe = false;
}
