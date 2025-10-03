package com.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "fines")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fineId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id", nullable = false)
    private Issue issue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @Column(nullable = false)
    private BigDecimal fineAmount;

    @Column(nullable = false)
    private Integer daysOverdue;

    @Column(nullable = false)
    private LocalDate generatedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processed_by_librarian_id")
    private User processedByLibrarian;

    private String waiverReason;

    private BigDecimal amountPaid = BigDecimal.ZERO;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public enum PaymentStatus {
        UNPAID, PAID, PARTIALLY_PAID, WAIVED
    }

    public enum PaymentMethod {
        CASH, ONLINE, UPI, CARD, WAIVED
    }

    // Helper methods
    public BigDecimal getRemainingAmount() {
        return fineAmount.subtract(amountPaid);
    }

    public boolean isFullyPaid() {
        return paymentStatus == PaymentStatus.PAID || 
               paymentStatus == PaymentStatus.WAIVED ||
               amountPaid.compareTo(fineAmount) >= 0;
    }
}
