package com.andersonrodriguez.literalura.service;

import com.andersonrodriguez.literalura.model.Libro;

import java.util.Comparator;
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

    public int mostrarLibrosEncontrados(List<DatosLibro> datosLibroList) {

        while(true){
            mostrarMensajeLibrosEncontrados();
            for (int i = 0; i < datosLibroList.size() ; i++) {
                System.out.printf("""
                         %d - Titulo: %s, Autores: %s, cantidad de descargas: %s
                         """,
                        i+1,
                        datosLibroList.get(i).titulo(),
                        datosLibroList.get(i).autores(),
                        datosLibroList.get(i).descargas());
            }
            System.out.println("\n0 - Volver al menu principal");
            System.out.println("\nIngrese el numero correspondiente al libro que desea añadir a la base de datos");
            String opcionIngresada = scanner.nextLine();
            if(Validacion.validarIngresoMenu(opcionIngresada, datosLibroList.size())){
                mostarErrorIngresoDatos();
                continue;
            }
            return Integer.parseInt(opcionIngresada);
        }
    }

    public void mostrarMensajeLibrosEncontrados(){
        System.out.println("""
                    *****************************************************************************************
                    ********************************** LIBROS ENCONTRADOS ***********************************
                    *****************************************************************************************
                    """);
    }

    public void mostrarExistenciaLibro(Libro libro) {
        System.out.printf("""
                ************************  EL LIBRO YA EXISTE EN LA BASE DE DATOS ************************
                libro: %s
                *****************************************************************************************
                """, libro.getTitulo());
    }

    public void mostrarMensajeLibroGuardado(Libro libro) {
        System.out.printf("""
                ******************************* LIBRO GUARDADO CON EXITO ********************************
                LIBRO: %s
                *****************************************************************************************
                """, libro.getTitulo());
    }

    public void MostrarLsitaVacia() {
        System.out.println("""
                *****************************************************************************************
                No hay ningun libro registrado en la base de datos
                *****************************************************************************************
                """);
    }

    public void mostrarLibros(List<Libro> librosEnLaBaseDeDatos) {
        System.out.println("""
                    *****************************************************************************************
                    ********************************** LIBROS ENCONTRADOS ***********************************
                    *****************************************************************************************
                    """);
        librosEnLaBaseDeDatos.stream()
                .sorted(Comparator.comparing(Libro::getCantidadDescargas).reversed())
                .forEach(a -> {
                    System.out.println("Titulo: " + a.getTitulo());
                    System.out.println("Cantidad de Descargas:" + a.getCantidadDescargas());
                    System.out.println("Autores: " + a.getAutorList());
                    System.out.println("*****************************************************************************************\n");
                });
    }
}
