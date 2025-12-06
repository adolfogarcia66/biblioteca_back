package com.unir.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(nullable = false, length = 255)
    private String author;

    @Column(name = "cover_image_url", length = 1000)
    private String coverImageUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 20)
    private String isbn;

    @Column(length = 10)
    private String language;

    private LocalDate publicationDate;

    @Column(length = 255)
    private String publisher;

    @Column(length = 255)
    private String subject;

    // 🔥 Relación ManyToOne a Category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")  // FK
    private Category category;
}