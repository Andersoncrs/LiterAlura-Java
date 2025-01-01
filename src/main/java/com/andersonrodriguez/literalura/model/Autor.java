package com.andersonrodriguez.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int fechaNacimiento;
    private int fechaMuerte;

    @ManyToMany(mappedBy = "autorList")
    private List<Libro> libroList;
}
