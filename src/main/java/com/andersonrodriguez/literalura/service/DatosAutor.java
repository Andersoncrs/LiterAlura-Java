package com.andersonrodriguez.literalura.service;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name")
        String nombre,

        @JsonAlias("birth_year")
        String fecha_Nacimiento,

        @JsonAlias("death_year")
        String fecha_Muerte
) {
    public String comprobarFechaNacimiento(){
        if(fecha_Nacimiento.equalsIgnoreCase("null")){
            return "0";
        }
        return fecha_Nacimiento;
    }

    @Override
    public String toString() {
        return "nombre: " + nombre +
               ", fecha nacimiento: " + fecha_Nacimiento +
               ", fecha de Muerte: " + fecha_Muerte;
    }
}
