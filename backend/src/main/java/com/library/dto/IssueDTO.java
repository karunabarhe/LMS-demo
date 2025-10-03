package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueDTO {
    
    private Long issueId;
    private Long studentId;
    private String studentName;
    private String studentIdNumber;
    private Long bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookIsbn;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Integer renewalCount;
    private String status;
    private Long issuedByLibrarianId;
    private String issuedByLibrarianName;
    private Long returnedToLibrarianId;
    private String returnedToLibrarianName;
    private String bookCondition;
    private String conditionNotes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Computed fields
    private Long daysRemaining;
    private Long daysOverdue;
    private Boolean isOverdue;
    private String statusColor; // GREEN, YELLOW, RED
}
