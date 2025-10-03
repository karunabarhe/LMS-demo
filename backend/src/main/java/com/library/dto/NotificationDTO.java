package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    
    private Long notificationId;
    private Long userId;
    private String notificationType;
    private String title;
    private String message;
    private Boolean isRead;
    private LocalDateTime sentAt;
    private LocalDateTime createdAt;
    private Long relatedEntityId;
    private String relatedEntityType;
}
