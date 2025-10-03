package com.library.repository;

import com.library.entity.Issue;
import com.library.entity.User;
import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    
    List<Issue> findByStudent(User student);
    
    List<Issue> findByStudentAndStatus(User student, Issue.IssueStatus status);
    
    List<Issue> findByBook(Book book);
    
    List<Issue> findByStatus(Issue.IssueStatus status);
    
    @Query("SELECT i FROM Issue i WHERE i.student.userId = :studentId AND i.status = 'ISSUED'")
    List<Issue> findCurrentIssuesByStudentId(@Param("studentId") Long studentId);
    
    @Query("SELECT i FROM Issue i WHERE i.status = 'ISSUED' AND i.dueDate < :date")
    List<Issue> findOverdueIssues(@Param("date") LocalDate date);
    
    @Query("SELECT i FROM Issue i WHERE i.status = 'ISSUED' AND i.dueDate BETWEEN :startDate AND :endDate")
    List<Issue> findIssuesDueBetween(@Param("startDate") LocalDate startDate, 
                                     @Param("endDate") LocalDate endDate);
    
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.student = :student AND i.status = 'ISSUED'")
    Long countCurrentIssuesByStudent(@Param("student") User student);
    
    @Query("SELECT i FROM Issue i WHERE i.status = 'RENEWAL_REQUESTED'")
    List<Issue> findPendingRenewals();
    
    @Query("SELECT i FROM Issue i WHERE i.issueDate BETWEEN :startDate AND :endDate")
    List<Issue> findIssuesBetweenDates(@Param("startDate") LocalDate startDate, 
                                       @Param("endDate") LocalDate endDate);
    
    @Query("SELECT i FROM Issue i WHERE i.returnDate BETWEEN :startDate AND :endDate")
    List<Issue> findReturnsBetweenDates(@Param("startDate") LocalDate startDate, 
                                        @Param("endDate") LocalDate endDate);
    
    @Query("SELECT i FROM Issue i WHERE i.student.userId = :studentId ORDER BY i.issueDate DESC")
    List<Issue> findIssueHistoryByStudentId(@Param("studentId") Long studentId);
    
    @Query("SELECT i FROM Issue i WHERE i.book.bookId = :bookId ORDER BY i.issueDate DESC")
    List<Issue> findIssueHistoryByBookId(@Param("bookId") Long bookId);
    
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.status = 'ISSUED'")
    Long countTotalIssuedBooks();
    
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.issueDate = :date")
    Long countIssuesOnDate(@Param("date") LocalDate date);
    
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.returnDate = :date")
    Long countReturnsOnDate(@Param("date") LocalDate date);
}
