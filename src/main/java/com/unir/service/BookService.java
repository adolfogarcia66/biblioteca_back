package com.unir.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unir.model.Book;
import com.unir.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }
}
 