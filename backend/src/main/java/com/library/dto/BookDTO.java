package com.library.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    
    private Long bookId;
    
    @NotBlank(message = "ISBN is required")
    private String isbn;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Author is required")
    private String author;
    
    private String publisher;
    private Integer publicationYear;
    private String edition;
    
    @NotBlank(message = "Category is required")
    private String category;
    
    private String language;
    
    @Min(value = 1, message = "Total copies must be at least 1")
    private Integer totalCopies;
    
    private Integer availableCopies;
    private String location;
    private String coverImageUrl;
    private String description;
    private String tags;
    private BigDecimal priceValue;
    private Integer totalIssueCount;
    private LocalDateTime lastIssuedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isAvailable;
}
