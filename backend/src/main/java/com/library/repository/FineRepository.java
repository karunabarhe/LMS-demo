package com.library.repository;

import com.library.entity.Fine;
import com.library.entity.User;
import com.library.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FineRepository extends JpaRepository<Fine, Long> {
    
    List<Fine> findByStudent(User student);
    
    List<Fine> findByStudentAndPaymentStatus(User student, Fine.PaymentStatus paymentStatus);
    
    Optional<Fine> findByIssue(Issue issue);
    
    List<Fine> findByPaymentStatus(Fine.PaymentStatus paymentStatus);
    
    @Query("SELECT f FROM Fine f WHERE f.student.userId = :studentId AND f.paymentStatus = 'UNPAID'")
    List<Fine> findUnpaidFinesByStudentId(@Param("studentId") Long studentId);
    
    @Query("SELECT SUM(f.fineAmount - f.amountPaid) FROM Fine f WHERE f.student = :student AND f.paymentStatus IN ('UNPAID', 'PARTIALLY_PAID')")
    BigDecimal calculateTotalOutstandingFinesByStudent(@Param("student") User student);
    
    @Query("SELECT SUM(f.amountPaid) FROM Fine f WHERE f.paymentStatus IN ('PAID', 'PARTIALLY_PAID') AND f.paymentDate BETWEEN :startDate AND :endDate")
    BigDecimal calculateTotalFineCollectionBetweenDates(@Param("startDate") LocalDate startDate, 
                                                        @Param("endDate") LocalDate endDate);
    
    @Query("SELECT SUM(f.fineAmount - f.amountPaid) FROM Fine f WHERE f.paymentStatus IN ('UNPAID', 'PARTIALLY_PAID')")
    BigDecimal calculateTotalOutstandingFines();
    
    @Query("SELECT f FROM Fine f WHERE f.paymentDate BETWEEN :startDate AND :endDate")
    List<Fine> findFinesPaidBetweenDates(@Param("startDate") LocalDate startDate, 
                                         @Param("endDate") LocalDate endDate);
    
    @Query("SELECT f FROM Fine f WHERE f.generatedDate BETWEEN :startDate AND :endDate")
    List<Fine> findFinesGeneratedBetweenDates(@Param("startDate") LocalDate startDate, 
                                              @Param("endDate") LocalDate endDate);
    
    @Query("SELECT COUNT(f) FROM Fine f WHERE f.paymentStatus = 'UNPAID'")
    Long countUnpaidFines();
    
    @Query("SELECT f FROM Fine f WHERE f.student.department = :department AND f.paymentStatus IN ('UNPAID', 'PARTIALLY_PAID')")
    List<Fine> findUnpaidFinesByDepartment(@Param("department") String department);
}
