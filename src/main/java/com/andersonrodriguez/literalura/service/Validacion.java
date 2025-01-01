package com.andersonrodriguez.literalura.service;

import jakarta.persistence.criteria.CriteriaBuilder;

public class Validacion {

    public static Boolean validarIngresoMenu(String opcionIngresada, int cantidadOpciones){
        if(opcionIngresada.matches("\\d") &&
           Integer.parseInt(opcionIngresada) >= 0 &&
           Integer.parseInt(opcionIngresada)<= cantidadOpciones){
            return false;
        }
        return true;
    }
}
