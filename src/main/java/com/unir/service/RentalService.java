package com.unir.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.unir.enums.RentalStatus;
import com.unir.model.Book;
import com.unir.model.Rental;
import com.unir.model.User;
import com.unir.repository.BookRepository;
import com.unir.repository.RentalRepository;
import com.unir.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    // Crear alquiler
    public Rental rentBook(String bookId, String userId) {
    	
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        User user = userRepository.findByEmail(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rental rental = Rental.builder()
                .book(book)
                .user(user)
                .startDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(14)) // 14 días por defecto
                .extensions(0)
                .status(RentalStatus.ACTIVE)
                .build();

        return rentalRepository.save(rental);
    }

    // Extender alquiler
    public Rental extendRental(String rentalId, int extraDays) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Alquiler no encontrado"));

        // Regla simple: máximo 3 extensiones
        if (rental.getExtensions() >= 3) {
            throw new RuntimeException("Límite de extensiones alcanzado");
        }

        rental.setDueDate(rental.getDueDate().plusDays(extraDays));
        rental.setExtensions(rental.getExtensions() + 1);

        return rentalRepository.save(rental);
    }

    // Devolver el libro
    public Rental returnBook(String rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Alquiler no encontrado"));

        rental.setStatus(RentalStatus.RETURNED);
        return rentalRepository.save(rental);
    }

    public Optional<Rental> getRental(String rentalId) {
        return rentalRepository.findById(rentalId);
    }
    
    public List<Rental> getActiveRentals() {
        return rentalRepository.findByStatus(RentalStatus.ACTIVE);
    }

    public List<Rental> getLateRentals() {
        List<Rental> all = rentalRepository.findAll();

        return all.stream()
                .filter(r -> r.getStatus() == RentalStatus.ACTIVE &&
                             r.getDueDate().isBefore(LocalDate.now()))
                .peek(r -> {
                    r.setStatus(RentalStatus.LATE);
                    rentalRepository.save(r);
                })
                .toList();
    }
    
    public List<Rental> getRentalsByUser(String userId) {
    	 User user = userRepository.findByEmail(userId)
                 .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return rentalRepository.findByUserIdAndStatus(user.getId(),RentalStatus.ACTIVE);
    }


}
