package com.andersonrodriguez.literalura.service;

import com.andersonrodriguez.literalura.model.Libro;
import com.andersonrodriguez.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public void AgregarLibro(Libro libro) {
        libroRepository.save(libro);
    }

    public boolean comprobarExistenciaLibro(Libro libro) {
        Optional<Libro> librobuscado = libroRepository.findByTitulo(libro.getTitulo());
        return librobuscado.isPresent();
    }

    public List<Libro> listarTodosLosLibros() {
        return libroRepository.findAll();
    }
}
