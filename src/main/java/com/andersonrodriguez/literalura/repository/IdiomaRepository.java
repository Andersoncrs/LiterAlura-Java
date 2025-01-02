package com.andersonrodriguez.literalura.repository;

import com.andersonrodriguez.literalura.model.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdiomaRepository extends JpaRepository<Idioma, Long> {
    Optional<Idioma> findByIdioma(String idioma);
}
