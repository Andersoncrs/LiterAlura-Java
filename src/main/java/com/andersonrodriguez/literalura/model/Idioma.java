package com.andersonrodriguez.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "idiomas")
public class Idioma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idioma;

    @ManyToMany(mappedBy = "idiomaList", fetch = FetchType.EAGER)
    private List<Libro> libroList;


    public Idioma(String idioma) {
        this.idioma = idioma;
    }

    public Idioma() {
    }

    public String getIdioma() {
        return idioma;
    }
}
