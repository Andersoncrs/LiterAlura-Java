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

    String titulo;
    int cantidadDescargas;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    List<Autor> autorList;

    public Libro(String titulo, String cantidadDescargas, List<DatosAutor> datosAutors) {
        this.titulo = titulo;
        this.cantidadDescargas = Integer.parseInt(cantidadDescargas);
        this.autorList = datosAutors.stream().
                map(a -> new Autor(a.nombre(),
                        (a.fechaNacimiento() != null && a.fechaNacimiento().matches("^-?\\d+$")) ? Integer.parseInt(a.fechaNacimiento()) : 0,
                        (a.fechaMuerte() != null && a.fechaMuerte().matches("^-?\\d+$")) ? Integer.parseInt(a.fechaMuerte()) : 0))
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

    @Override
    public String toString() {
        return "\"" + titulo + "\"";
    }
}
