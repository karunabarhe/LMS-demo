package com.library.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReturnBookRequest {
    
    @NotNull(message = "Issue ID is required")
    private Long issueId;
    
    private String bookCondition; // GOOD, FAIR, DAMAGED, LOST
    private String conditionNotes;
}
