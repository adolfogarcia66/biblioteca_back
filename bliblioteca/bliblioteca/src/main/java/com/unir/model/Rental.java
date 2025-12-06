package com.unir.model;

import java.time.LocalDate;

import com.unir.enums.RentalStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rentals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	// Libro alquilado
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;

	// Usuario que hace el alquiler (si lo estás manejando)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	// Fecha de inicio del alquiler
	@Column(nullable = false)
	private LocalDate startDate;

	// Fecha límite actual
	@Column(nullable = false)
	private LocalDate dueDate;

	// Número de extensiones ya hechas
	@Column(nullable = false)
	private int extensions;

	// Estado del alquiler
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private RentalStatus status;
}
