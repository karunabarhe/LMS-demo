package com.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String message;

    @Column(nullable = false)
    private Boolean isRead = false;

    @Column(nullable = false)
    private LocalDateTime sentAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Reference to related entity (optional)
    private Long relatedEntityId;
    private String relatedEntityType; // BOOK, ISSUE, FINE, WISHLIST

    public enum NotificationType {
        DUE_DATE_REMINDER,
        OVERDUE_NOTICE,
        FINE_GENERATED,
        FINE_REMINDER,
        WISHLIST_AVAILABLE,
        WISHLIST_STATUS_UPDATE,
        BOOK_ISSUED,
        BOOK_RETURNED,
        RENEWAL_APPROVED,
        RENEWAL_REJECTED,
        RESERVATION_AVAILABLE,
        GENERAL_NOTICE,
        ACCOUNT_SUSPENDED,
        PASSWORD_RESET
    }
}
