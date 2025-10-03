package com.library.dto;

import jakarta.validation.constraints.NotBlank;
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
public class WishlistDTO {
    
    private Long wishlistId;
    private Long studentId;
    private String studentName;
    
    @NotBlank(message = "Book title is required")
    private String bookTitle;
    
    @NotBlank(message = "Author is required")
    private String author;
    
    private String isbn;
    private String publisher;
    private String reasonComment;
    private LocalDate requestDate;
    private String status;
    private String priority;
    private String rejectionReason;
    private Long statusUpdatedByLibrarianId;
    private String statusUpdatedByLibrarianName;
    private LocalDate estimatedAvailabilityDate;
    private Integer requestCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
