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

    @ManyToMany(mappedBy = "autorList", fetch = FetchType.EAGER)
    private List<Libro> libroList;

    public Autor(String nombre, int fechaNacimiento, int fechaMuerte) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaMuerte = fechaMuerte;
    }

    public Autor() {
    }

    public String getNombre() {
        return nombre;
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getFechaMuerte() {
        return fechaMuerte;
    }

    @Override
    public String toString() {
        return "nombre= " + nombre + ", fechaNacimiento= " + fechaNacimiento + ", fechaMuerte= " + fechaMuerte;
    }
}
