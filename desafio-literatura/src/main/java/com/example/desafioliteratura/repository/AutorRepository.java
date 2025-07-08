package com.example.desafioliteratura.repository;

import com.example.desafioliteratura.model.Autor;
import com.example.desafioliteratura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombre);

}
