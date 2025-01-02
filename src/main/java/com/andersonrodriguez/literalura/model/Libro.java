package com.andersonrodriguez.literalura.model;

import com.andersonrodriguez.literalura.service.DatosAutor;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private int cantidadDescargas;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autorList;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_idioma",
            joinColumns = @JoinColumn(name= "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "idioma_id")
    )
    private List<Idioma> idiomaList;


    public Libro(String titulo, String cantidadDescargas, List<DatosAutor> datosAutors , List<String> stringList) {
        this.titulo = titulo;
        this.cantidadDescargas = Integer.parseInt(cantidadDescargas);
        this.autorList = datosAutors.stream().
                map(a -> new Autor(a.nombre(),
                        (a.fechaNacimiento() != null && a.fechaNacimiento().matches("^-?\\d+$")) ? Integer.parseInt(a.fechaNacimiento()) : 0,
                        (a.fechaMuerte() != null && a.fechaMuerte().matches("^-?\\d+$")) ? Integer.parseInt(a.fechaMuerte()) : 0))
                .collect(Collectors.toList());
        this.idiomaList = stringList.stream()
                .map(Idioma::new)
                .collect(Collectors.toList());
    }

    public Libro() {
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Autor> getAutorList() {
        return autorList;
    }

    public int getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setAutorList(List<Autor> autorList) {
        this.autorList = autorList;
    }

    public List<Idioma> getIdiomaList() {
        return idiomaList;
    }

    public void setIdiomaList(List<Idioma> idiomaList) {
        this.idiomaList = idiomaList;
    }

    @Override
    public String toString() {
        return "\"" + titulo + "\"";
    }
}
