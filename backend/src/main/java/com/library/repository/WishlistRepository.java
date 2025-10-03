package com.library.repository;

import com.library.entity.Wishlist;
import com.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    
    List<Wishlist> findByStudent(User student);
    
    List<Wishlist> findByStatus(Wishlist.WishlistStatus status);
    
    List<Wishlist> findByPriority(Wishlist.Priority priority);
    
    @Query("SELECT w FROM Wishlist w WHERE w.student.userId = :studentId")
    List<Wishlist> findByStudentId(@Param("studentId") Long studentId);
    
    @Query("SELECT w FROM Wishlist w WHERE LOWER(w.bookTitle) = LOWER(:title) AND LOWER(w.author) = LOWER(:author)")
    List<Wishlist> findByBookTitleAndAuthor(@Param("title") String title, @Param("author") String author);
    
    @Query("SELECT w FROM Wishlist w WHERE w.isbn = :isbn")
    List<Wishlist> findByIsbn(@Param("isbn") String isbn);
    
    @Query("SELECT w FROM Wishlist w WHERE w.status = 'PENDING' ORDER BY w.requestCount DESC, w.requestDate ASC")
    List<Wishlist> findPendingWishlistsByPriority();
    
    @Query("SELECT w FROM Wishlist w WHERE w.priority = 'HIGH' AND w.status IN ('PENDING', 'UNDER_CONSIDERATION')")
    List<Wishlist> findHighPriorityPendingWishlists();
    
    @Query("SELECT COUNT(w) FROM Wishlist w WHERE LOWER(w.bookTitle) = LOWER(:title) AND LOWER(w.author) = LOWER(:author) AND w.status IN ('PENDING', 'UNDER_CONSIDERATION')")
    Long countRequestsForBook(@Param("title") String title, @Param("author") String author);
    
    @Query("SELECT w FROM Wishlist w WHERE w.status IN ('PENDING', 'UNDER_CONSIDERATION', 'APPROVED', 'ON_ORDER') ORDER BY w.priority DESC, w.requestCount DESC")
    List<Wishlist> findAllActiveWishlists();
}
