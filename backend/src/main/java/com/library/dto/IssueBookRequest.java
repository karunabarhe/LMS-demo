package com.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IssueBookRequest {
    
    @NotBlank(message = "Student ID is required")
    private String studentId;
    
    @NotBlank(message = "Book ISBN is required")
    private String bookIsbn;
    
    private LocalDate customDueDate; // Optional: for manual adjustment
    private String notes;
}
