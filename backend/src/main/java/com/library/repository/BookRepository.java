package com.library.repository;

import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    Optional<Book> findByIsbn(String isbn);
    
    boolean existsByIsbn(String isbn);
    
    List<Book> findByCategory(String category);
    
    List<Book> findByLanguage(String language);
    
    List<Book> findByAuthorContainingIgnoreCase(String author);
    
    List<Book> findByAvailableCopiesGreaterThan(Integer copies);
    
    @Query("SELECT DISTINCT b.category FROM Book b ORDER BY b.category")
    List<String> findAllCategories();
    
    @Query("SELECT b FROM Book b WHERE " +
           "LOWER(b.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(b.author) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(b.isbn) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(b.publisher) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Book> searchBooks(@Param("searchTerm") String searchTerm);
    
    @Query("SELECT b FROM Book b WHERE b.availableCopies > 0")
    List<Book> findAllAvailableBooks();
    
    @Query("SELECT b FROM Book b WHERE b.availableCopies = 0")
    List<Book> findAllUnavailableBooks();
    
    @Query("SELECT b FROM Book b WHERE b.availableCopies < :threshold AND b.totalCopies > 0")
    List<Book> findLowStockBooks(@Param("threshold") Integer threshold);
    
    @Query("SELECT b FROM Book b ORDER BY b.totalIssueCount DESC")
    List<Book> findMostPopularBooks();
    
    @Query("SELECT b FROM Book b ORDER BY b.createdAt DESC")
    List<Book> findRecentlyAddedBooks();
}
