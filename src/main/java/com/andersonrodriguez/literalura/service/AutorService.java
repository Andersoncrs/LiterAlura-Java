package com.andersonrodriguez.literalura.service;

import com.andersonrodriguez.literalura.model.Autor;
import com.andersonrodriguez.literalura.model.Libro;
import com.andersonrodriguez.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> comprobarExistenciaAutores(Libro libro) {
        List<Autor> autorList = new ArrayList<>();
        for (Autor autor : libro.getAutorList()) {
            Optional<Autor> busquedaAutor = autorRepository.findByNombre(autor.getNombre());
            if (busquedaAutor.isPresent()) {
                autorList.add(busquedaAutor.get());
            } else {
                autorRepository.save(autor);
                autorList.add(autor);
            }
        }
        return autorList;
    }

    public List<Autor> listarTodosLosAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> BuscarAutoresVivosPorFecha(int fechaIngresada) {
        return autorRepository.BuscarAutoresVivosPorFecha(fechaIngresada);
    }
}
