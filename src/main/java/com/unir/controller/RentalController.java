package com.unir.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unir.model.Rental;
import com.unir.service.RentalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @PostMapping
    public Rental rentBook(@RequestParam String bookId, @RequestParam String userId) {
        return rentalService.rentBook(bookId, userId);
    }

    @PostMapping("/{id}/extend")
    public Rental extendRental(@PathVariable String id, @RequestParam int days) {
        return rentalService.extendRental(id, days);
    }

    @PostMapping("/{id}/return")
    public Rental returnBook(@PathVariable String id) {
        return rentalService.returnBook(id);
    }

    @GetMapping("/{id}")
    public Rental getRental(@PathVariable String id) {
        return rentalService.getRental(id)
                .orElseThrow(() -> new RuntimeException("Alquiler no encontrado"));
    }
    
    @GetMapping("/active")
    public List<Rental> getActiveRentals() {
        return rentalService.getActiveRentals();
    }

    @GetMapping("/late")
    public List<Rental> getLateRentals() {
        return rentalService.getLateRentals();
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<?> getRentalsByUser(@PathVariable  String userId) {
        return ResponseEntity.ok(rentalService.getRentalsByUser(userId));
    }

}
