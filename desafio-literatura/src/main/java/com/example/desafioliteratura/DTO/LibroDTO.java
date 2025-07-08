package com.example.desafioliteratura.DTO;

import com.example.desafioliteratura.model.Autor;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

public record LibroDTO(
       Long id,
       String titulo,
       Autor autor,
       String idioma,
       Double descargasTotales
) {
}
