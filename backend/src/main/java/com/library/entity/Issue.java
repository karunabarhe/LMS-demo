package com.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "issues")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private LocalDate issueDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    private LocalDate returnDate;

    @Column(nullable = false)
    private Integer renewalCount = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IssueStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issued_by_librarian_id", nullable = false)
    private User issuedByLibrarian;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "returned_to_librarian_id")
    private User returnedToLibrarian;

    @Enumerated(EnumType.STRING)
    private BookCondition bookCondition;

    private String conditionNotes;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public enum IssueStatus {
        ISSUED, RETURNED, OVERDUE, RENEWAL_REQUESTED, LOST, DAMAGED
    }

    public enum BookCondition {
        GOOD, FAIR, DAMAGED, LOST
    }

    // Helper methods
    public boolean isOverdue() {
        return status == IssueStatus.ISSUED && LocalDate.now().isAfter(dueDate);
    }

    public long getDaysOverdue() {
        if (isOverdue()) {
            return java.time.temporal.ChronoUnit.DAYS.between(dueDate, LocalDate.now());
        }
        return 0;
    }

    public long getDaysRemaining() {
        if (status == IssueStatus.ISSUED && !isOverdue()) {
            return java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
        }
        return 0;
    }
}
