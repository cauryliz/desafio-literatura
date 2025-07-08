package com.example.desafioliteratura.service;

import com.example.desafioliteratura.model.Autor;
import com.example.desafioliteratura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

   @Autowired
    private AutorRepository autorRepository;

    public Autor buscarOActualizarAutor(Autor autorNuevo) {
        Optional<Autor> autorExistenteOpt = autorRepository.findByNombre(autorNuevo.getNombre());

        if (autorExistenteOpt.isPresent()) {
            Autor autorExistente = autorExistenteOpt.get();

            if ((autorExistente.getAnioNacimiento() == null || autorExistente.getAnioNacimiento().isBlank())
                    && autorNuevo.getAnioNacimiento() != null) {
                autorExistente.setAnioNacimiento(autorNuevo.getAnioNacimiento());
            }

            if ((autorExistente.getAnioMuerte() == null || autorExistente.getAnioMuerte().isBlank())
                    && autorNuevo.getAnioMuerte() != null) {
                autorExistente.setAnioMuerte(autorNuevo.getAnioMuerte());
            }

            return autorRepository.save(autorExistente);
        } else {
            return autorRepository.save(autorNuevo);
        }
    }

    public List<Autor> obtenerTodos() {
        return autorRepository.findAll();
    }

    public List<Autor> buscarAutoresVivosEn(int anio) {
        return autorRepository.findAll().stream()
                .filter(a -> {
                    try {
                        int nacimiento = Integer.parseInt(a.getAnioNacimiento());
                        String muerteStr = a.getAnioMuerte();
                        return nacimiento <= anio && (muerteStr == null || muerteStr.isBlank() || Integer.parseInt(muerteStr) >= anio);
                    } catch (Exception e) {
                        return false;
                    }
                }).toList();
    }
}
