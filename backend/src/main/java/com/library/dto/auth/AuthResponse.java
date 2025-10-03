package com.library.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    
    private String token;
    private String refreshToken;
    private String type = "Bearer";
    private Long userId;
    private String fullName;
    private String email;
    private String role;
    private String studentId;
    private String profileImageUrl;
}
