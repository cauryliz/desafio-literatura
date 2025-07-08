package com.example.desafioliteratura.service;

import com.example.desafioliteratura.model.Autor;
import com.example.desafioliteratura.model.Libro;
import com.example.desafioliteratura.repository.AutorRepository;
import com.example.desafioliteratura.repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;
    @Transactional
    public void guardarLibro(Libro libro) {
        if (libroRepository.findByTitulo(libro.getTitulo()).isPresent()) {
            System.out.println("El libro ya está registrado: " + libro.getTitulo());
            return;
        }

        String nombreAutor = libro.getAutor().getNombre();
        Autor autorExistente = autorRepository
                .findByNombre(nombreAutor)
                .orElseGet(() -> autorRepository.save(libro.getAutor()));

        // Actualizar datos faltantes si es necesario
        if ((autorExistente.getAnioNacimiento() == null || autorExistente.getAnioNacimiento().isBlank())
                && libro.getAutor().getAnioNacimiento() != null) {
            autorExistente.setAnioNacimiento(libro.getAutor().getAnioNacimiento());
        }

        if ((autorExistente.getAnioMuerte() == null || autorExistente.getAnioMuerte().isBlank())
                && libro.getAutor().getAnioMuerte() != null) {
            autorExistente.setAnioMuerte(libro.getAutor().getAnioMuerte());
        }

        autorRepository.save(autorExistente);
        libro.setAutor(autorExistente);
        libroRepository.save(libro);
    }

//    public void guardarLibro(Libro libro){
//        if (libroRepository.findByTitulo(libro.getTitulo()).isPresent()) {
//            System.out.println("El libro ya está registrado: " + libro.getTitulo());
//            return;
//        }
//        String nombreAutor = libro.getAutor().getNombre();
//
//        Autor autorExistente = autorRepository
//                .findByNombre(nombreAutor)
//                .orElseGet(() -> autorRepository.save(libro.getAutor())); // si no existe, lo guarda
//
//        libro.setAutor(autorExistente);
//        libroRepository.save(libro);
//    }
}
