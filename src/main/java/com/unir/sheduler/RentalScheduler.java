package com.unir.sheduler;


import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.unir.enums.RentalStatus;
import com.unir.model.Rental;
import com.unir.repository.RentalRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RentalScheduler {

    private final RentalRepository rentalRepository;

    // Ejecuta todos los días a medianoche
    @Scheduled(cron = "0 0 0 * * *")
    public void updateLateRentals() {

        LocalDate today = LocalDate.now();

        List<Rental> activeRentals = rentalRepository.findByStatus(RentalStatus.ACTIVE);

        activeRentals.stream()
                .filter(rental -> rental.getDueDate().isBefore(today))
                .forEach(rental -> {
                    rental.setStatus(RentalStatus.LATE);
                    rentalRepository.save(rental);
                    log.info("Alquiler {} marcado como LATE", rental.getId());
                });
    }
}
