package com.andersonrodriguez.literalura.service;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name")
        String nombre,

        @JsonAlias("birth_year")
        String fechaNacimiento,

        @JsonAlias("death_year")
        String fechaMuerte
) {
    public String comprobarFechaNacimiento(){
        if(fechaNacimiento.equalsIgnoreCase("null")){
            return "0";
        }
        return fechaNacimiento;
    }

    @Override
    public String toString() {
        return "nombre: " + nombre +
               ", fecha nacimiento: " + fechaNacimiento +
               ", fecha de Muerte: " + fechaMuerte;
    }
}
