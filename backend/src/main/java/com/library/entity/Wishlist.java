package com.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "wishlist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @NotBlank(message = "Book title is required")
    @Column(nullable = false)
    private String bookTitle;

    @NotBlank(message = "Author is required")
    @Column(nullable = false)
    private String author;

    private String isbn;

    private String publisher;

    @Column(length = 500)
    private String reasonComment;

    @Column(nullable = false)
    private LocalDate requestDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WishlistStatus status = WishlistStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.LOW;

    private String rejectionReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_updated_by_librarian_id")
    private User statusUpdatedByLibrarian;

    private LocalDate estimatedAvailabilityDate;

    private Integer requestCount = 1; // Number of students requesting this book

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public enum WishlistStatus {
        PENDING, UNDER_CONSIDERATION, APPROVED, ON_ORDER, ADDED_TO_LIBRARY, REJECTED
    }

    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    // Helper method to auto-update priority based on request count
    public void updatePriority() {
        if (requestCount >= 5) {
            this.priority = Priority.HIGH;
        } else if (requestCount >= 2) {
            this.priority = Priority.MEDIUM;
        } else {
            this.priority = Priority.LOW;
        }
    }
}
