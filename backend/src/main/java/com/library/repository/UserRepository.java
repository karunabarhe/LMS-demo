package com.library.repository;

import com.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    Optional<User> findByStudentId(String studentId);
    
    Optional<User> findByEmailOrStudentId(String email, String studentId);
    
    boolean existsByEmail(String email);
    
    boolean existsByStudentId(String studentId);
    
    List<User> findByRole(User.Role role);
    
    List<User> findByRoleAndStatus(User.Role role, User.AccountStatus status);
    
    List<User> findByDepartment(String department);
    
    @Query("SELECT u FROM User u WHERE u.role = 'STUDENT' AND u.status = 'ACTIVE'")
    List<User> findAllActiveStudents();
    
    @Query("SELECT u FROM User u WHERE u.role = 'STUDENT' AND " +
           "(SELECT COUNT(i) FROM Issue i WHERE i.student.userId = u.userId AND i.status = 'ISSUED') > 0")
    List<User> findStudentsWithIssuedBooks();
    
    @Query("SELECT u FROM User u WHERE u.role = 'STUDENT' AND " +
           "EXISTS (SELECT f FROM Fine f WHERE f.student.userId = u.userId AND f.paymentStatus = 'UNPAID')")
    List<User> findStudentsWithUnpaidFines();
}
