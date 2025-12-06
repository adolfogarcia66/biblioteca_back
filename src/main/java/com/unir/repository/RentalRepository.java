package com.unir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unir.enums.RentalStatus;
import com.unir.model.Rental;

public interface RentalRepository extends JpaRepository<Rental, String> {
	List<Rental> findByStatus(RentalStatus status);
	List<Rental> findByUserId(String userId);
	List<Rental> findByUserIdAndStatus(String string, RentalStatus status);

}