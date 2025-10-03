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
public class FineDTO {
    
    private Long fineId;
    private Long issueId;
    private Long studentId;
    private String studentName;
    private String studentIdNumber;
    private String bookTitle;
    private String bookAuthor;
    private BigDecimal fineAmount;
    private Integer daysOverdue;
    private LocalDate generatedDate;
    private String paymentStatus;
    private LocalDate paymentDate;
    private String paymentMethod;
    private Long processedByLibrarianId;
    private String processedByLibrarianName;
    private String waiverReason;
    private BigDecimal amountPaid;
    private BigDecimal remainingAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
