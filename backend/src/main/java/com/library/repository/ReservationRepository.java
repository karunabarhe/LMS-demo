package com.library.repository;

import com.library.entity.Reservation;
import com.library.entity.User;
import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    List<Reservation> findByStudent(User student);
    
    List<Reservation> findByBook(Book book);
    
    List<Reservation> findByStatus(Reservation.ReservationStatus status);
    
    @Query("SELECT r FROM Reservation r WHERE r.book = :book AND r.status = 'ACTIVE' ORDER BY r.reservationDate ASC")
    List<Reservation> findActiveReservationsByBookOrderByDate(@Param("book") Book book);
    
    @Query("SELECT r FROM Reservation r WHERE r.student = :student AND r.book = :book AND r.status = 'ACTIVE'")
    Optional<Reservation> findActiveReservationByStudentAndBook(@Param("student") User student, 
                                                                 @Param("book") Book book);
    
    @Query("SELECT r FROM Reservation r WHERE r.status = 'ACTIVE' AND r.expiryDate < :date")
    List<Reservation> findExpiredReservations(@Param("date") LocalDateTime date);
    
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.book = :book AND r.status = 'ACTIVE'")
    Long countActiveReservationsForBook(@Param("book") Book book);
    
    @Query("SELECT r FROM Reservation r WHERE r.student.userId = :studentId AND r.status = 'ACTIVE'")
    List<Reservation> findActiveReservationsByStudentId(@Param("studentId") Long studentId);
}
