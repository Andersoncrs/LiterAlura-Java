package com.andersonrodriguez.literalura.service;


import com.andersonrodriguez.literalura.model.Idioma;
import com.andersonrodriguez.literalura.model.Libro;
import com.andersonrodriguez.literalura.repository.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IdiomaService {

    @Autowired
    IdiomaRepository idiomaRepository;

    public List<Idioma> comprobarExistenciaIdiomas(Libro libro){
        List<Idioma> idiomaList = new ArrayList<>();
        for (Idioma idioma : libro.getIdiomaList()) {
            Optional<Idioma> busquedaIdioma = idiomaRepository.findByIdioma(idioma.getIdioma());
            if(busquedaIdioma.isPresent()){
                idiomaList.add((busquedaIdioma.get()));
            } else {
                idiomaRepository.save(idioma);
                idiomaList.add(idioma);
            }

        }
        return idiomaList;
    }
}
