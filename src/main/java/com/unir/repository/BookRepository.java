package com.unir.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unir.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {
}