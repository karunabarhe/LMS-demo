package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {
    
    // Common fields
    private String userName;
    private String role;
    
    // Student Dashboard
    private Integer booksIssued;
    private Integer upcomingDueDates; // Next 7 days
    private BigDecimal outstandingFines;
    private Integer wishlistItems;
    private Integer unreadNotifications;
    private List<IssueDTO> currentIssues;
    private List<NotificationDTO> recentNotifications;
    private List<BookDTO> featuredBooks;
    
    // Librarian Dashboard
    private Long totalBooks;
    private Long booksIssued;
    private Long availableBooks;
    private Long overdueBooks;
    private BigDecimal totalOutstandingFines;
    private Long activeStudents;
    private Long todayIssues;
    private Long todayReturns;
    private List<IssueDTO> recentIssues;
    private List<IssueDTO> overdueIssuesList;
    private List<WishlistDTO> highPriorityWishlists;
    private List<BookDTO> lowStockBooks;
}
