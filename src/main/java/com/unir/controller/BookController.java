package com.unir.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unir.model.Book;
import com.unir.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

 
    @GetMapping("/carousel")
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String search) {
        List<Book> books;
        if (search == null || search.isBlank()) {
            books = bookService.getAll(); // todos los libros
        } else {
            books = bookService.searchBooks(search); // filtra por título o autor
        }
        return ResponseEntity.ok(books);
    }
}
   