package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    
    private Long userId;
    private String role;
    private String email;
    private String fullName;
    private String mobile;
    private String studentId;
    private LocalDate enrollmentDate;
    private LocalDate dateOfBirth;
    private String department;
    private String course;
    private String status;
    private String profileImageUrl;
    private Boolean emailNotifications;
    private Boolean smsNotifications;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Additional fields for student management
    private Integer booksIssued;
    private BigDecimal outstandingFine;
}
