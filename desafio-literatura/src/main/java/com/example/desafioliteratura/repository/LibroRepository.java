package com.example.desafioliteratura.repository;

import com.example.desafioliteratura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTitulo(String titulo);

    @Query("SELECT l FROM Libro l JOIN FETCH l.autor WHERE l.id = :id")
    Optional<Libro> findByIdConAutor(@Param("id") Long id);

}
