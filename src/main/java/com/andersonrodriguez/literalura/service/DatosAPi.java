package com.andersonrodriguez.literalura.service;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAPi(
        @JsonAlias("results")
        List<DatosLibro> resultados
) {

}
