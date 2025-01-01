package com.andersonrodriguez.literalura.service;

public class Validacion {

    public static Boolean validarIngresoMenu(String opcionIngresada, int cantidadOpciones){
        return !opcionIngresada.matches("\\d") ||
               (Integer.parseInt(opcionIngresada) < 0) ||
               (Integer.parseInt(opcionIngresada) > cantidadOpciones);
    }
}
