package com.unir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unir.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);

}