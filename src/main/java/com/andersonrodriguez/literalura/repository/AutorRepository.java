package com.andersonrodriguez.literalura.repository;

import com.andersonrodriguez.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombre(String nombre);

    @Query(value = "SELECT a FROM Autor AS a WHERE fechaNacimiento <= :fechaIngresada AND fechaMuerte >= :fechaIngresada")
    List<Autor> BuscarAutoresVivosPorFecha(@Param("fechaIngresada") int fechaIngresada);

}
