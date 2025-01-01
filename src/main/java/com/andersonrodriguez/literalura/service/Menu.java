package com.andersonrodriguez.literalura.service;

import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);

    public void mostrarBienvenida(){
        System.out.println("""
                
                *****************************************************************************************
                ******************************** BIENVENIDO A LITERALURA ********************************
                *****************************************************************************************
                """);
    }

    public int mostrarMenuPrincipal() {
        while(true){
            System.out.println("""
                    Elija la opción que desea:
                    
                    1 - Buscar Libro por Titulo
                    2 - Listar Libros Registrados
                    3 - Listar Autores Registrados
                    4 - Listar Autores vivos en un determinado año
                    5 - listar libros por idioma
                    
                    0 - Salir
                    """);
            String opcionIngresada = scanner.nextLine().trim();
            if(Validacion.validarIngresoMenu(opcionIngresada, 5)){
                mostarErrorIngresoDatos();
                continue;
            }
            return Integer.parseInt(opcionIngresada);
        }
    }

    private void mostarErrorIngresoDatos(){
        System.out.println("""
                *****************************************************************************************
                Error: Ha ingresado un valor invalido. Intentelo de Nuevo Por favor
                *****************************************************************************************
                """);
    }

    public String mostrarMenuBusquedaTitulo() {
        System.out.println("Ingrese el titulo que desea Buscar");
        return scanner.nextLine().trim().toLowerCase();
    }

    public void mostrarResultadoNoEncontrado(String titulo) {
        System.out.printf("""
                *****************************************************************************************
                El Titulo Buscado: "%s" No ha sido encontrado
                *****************************************************************************************
                """, titulo);
    }

    public void mostrarLibrosEncontrados(List<DatosLibro> datosLibroList) {
        for (int i = 0; i < datosLibroList.size() ; i++) {
            System.out.printf("""
                     %d - Titulo: %s, Autores: %s, cantidad de descargas: %s
                     """,
                    i+1,
                    datosLibroList.get(i).titulo(),
                    datosLibroList.get(i).autores(),
                    datosLibroList.get(i).descargas());
        }
    }
}
