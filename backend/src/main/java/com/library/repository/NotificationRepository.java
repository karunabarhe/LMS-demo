package com.library.repository;

import com.library.entity.Notification;
import com.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    
    List<Notification> findByUser(User user);
    
    List<Notification> findByUserAndIsRead(User user, Boolean isRead);
    
    List<Notification> findByUserAndNotificationType(User user, Notification.NotificationType notificationType);
    
    @Query("SELECT n FROM Notification n WHERE n.user.userId = :userId ORDER BY n.sentAt DESC")
    List<Notification> findByUserIdOrderBySentAtDesc(@Param("userId") Long userId);
    
    @Query("SELECT n FROM Notification n WHERE n.user.userId = :userId AND n.isRead = false ORDER BY n.sentAt DESC")
    List<Notification> findUnreadNotificationsByUserId(@Param("userId") Long userId);
    
    @Query("SELECT COUNT(n) FROM Notification n WHERE n.user.userId = :userId AND n.isRead = false")
    Long countUnreadNotificationsByUserId(@Param("userId") Long userId);
    
    @Query("SELECT n FROM Notification n WHERE n.sentAt < :date")
    List<Notification> findNotificationsOlderThan(@Param("date") LocalDateTime date);
    
    void deleteByUserAndIsReadAndSentAtBefore(User user, Boolean isRead, LocalDateTime date);
}
