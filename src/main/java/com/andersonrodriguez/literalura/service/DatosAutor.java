package com.andersonrodriguez.literalura.service;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name")
        String nombre,

        @JsonAlias("birth_year")
        String fecha_Nacimiento,

        @JsonAlias("death_year")
        String fecha_Muerte
) {
}
