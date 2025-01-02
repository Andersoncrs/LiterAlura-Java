package com.andersonrodriguez.literalura.service;

import com.andersonrodriguez.literalura.model.Autor;
import com.andersonrodriguez.literalura.model.Idioma;
import com.andersonrodriguez.literalura.model.Libro;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);

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
                mostrarErrorIngresoDatos();
                continue;
            }
            return Integer.parseInt(opcionIngresada);
        }
    }

    private void mostrarErrorIngresoDatos(){
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
            mostrarMensajeRegistrosEncontrados("LIBROS");
            for (int i = 0; i < datosLibroList.size() ; i++) {
                System.out.printf("""
                         %d - Titulo: %s, cantidad de descargas: %s, Idiomas: %s, Autores: %s
                         """,
                        i+1,
                        datosLibroList.get(i).titulo(),
                        datosLibroList.get(i).descargas(),
                        datosLibroList.get(i).idiomas(),
                        datosLibroList.get(i).autores()
                        );
            }
            System.out.println("\n0 - Volver al menu principal");
            System.out.println("\nIngrese el numero correspondiente al libro que desea añadir a la base de datos");
            String opcionIngresada = scanner.nextLine();
            if(Validacion.validarIngresoMenu(opcionIngresada, datosLibroList.size())){
                mostrarErrorIngresoDatos();
                continue;
            }
            return Integer.parseInt(opcionIngresada);
        }
    }

    public void mostrarMensajeRegistrosEncontrados(String tipoLista){
        System.out.printf("""
                    *****************************************************************************************
                    ********************************** %s ENCONTRADOS ***********************************
                    *****************************************************************************************
                    """, tipoLista);
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

    public void mostrarListaVacia(String tipoLista) {
        System.out.printf("""
                *****************************************************************************************
                No hay ningun %s registrado en la base de datos
                *****************************************************************************************
                """, tipoLista);
    }

    public void mostrarLibros(List<Libro> librosEnLaBaseDeDatos) {
        mostrarMensajeRegistrosEncontrados("LIBROS");
        librosEnLaBaseDeDatos.stream()
                .sorted(Comparator.comparing(Libro::getCantidadDescargas).reversed())
                .forEach(a -> {
                    System.out.println("Titulo: " + a.getTitulo());
                    System.out.println("Idiomas: " + a.getIdiomaList());
                    System.out.println("Cantidad de Descargas:" + a.getCantidadDescargas());
                    System.out.println("Autores: " + a.getAutorList());
                    System.out.println("*****************************************************************************************\n");
                });
    }

    public void mostrarAutores(List<Autor> autoresEnLaBaseDeDatos) {
        mostrarMensajeRegistrosEncontrados("AUTORES");
        autoresEnLaBaseDeDatos.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(a -> {
                    System.out.println("Nombre: " + a.getNombre());
                    System.out.println("Fecha de Nacimiento: " +
                            ((a.getFechaNacimiento()==0) ? "No Determinada" : a.getFechaNacimiento()));
                    System.out.println("Fecha de Muerte: " +
                            ((a.getFechaMuerte()== 0) ? "No Determinada" : a.getFechaMuerte()));
                    System.out.println("Libros: " + a.getLibroList());
                    System.out.println("*****************************************************************************************\n");

                });
    }

    public int solicitarFechaIngreso() {
        while(true){
            System.out.println("Por favor ingrese la Fecha que desea consultar");
            String ingresoUsuario = scanner.nextLine().trim();
            if(Validacion.validarNumeroEntero(ingresoUsuario)) {
                return Integer.parseInt(ingresoUsuario);
            }
            mostrarErrorIngresoDatos();
        }
    }

    public void mostrarFechaNoEncontrada(int fechaIngresada) {
        System.out.printf("""
                *****************************************************************************************
                No existe un Autor vivo en la fecha %d dentro de la base de datos
                *****************************************************************************************
                """, fechaIngresada);
    }

    public int mostrarIdiomasDisponibles(List<Idioma> idiomasDisponibles) {
        while(true){
            mostrarMensajeRegistrosEncontrados("IDIOMAS");
            for (int i = 0; i <idiomasDisponibles.size(); i++) {
                System.out.println(i + 1 + " - " + idiomasDisponibles.get(i).getIdioma());
            }
            System.out.println("\n0 - Volver al menu principal");
            System.out.println("\nIngrese el numero correspondiente al idioma que desea consultar");
            String opcionIngresada = scanner.nextLine();
            if(Validacion.validarIngresoMenu(opcionIngresada, idiomasDisponibles.size())){
                mostrarErrorIngresoDatos();
                continue;
            }
            return Integer.parseInt(opcionIngresada);
        }
    }

    public void MostrarDespedida() {
        System.out.println("""
                
                *****************************************************************************************
                ************************ GRACIAS POR UTILIZAR NUESTROS SERVICIOS ************************
                ************************************** LITERALURA ***************************************
                *****************************************************************************************
                *************************** REALIZADO POR ANDERSON RODRIGUEZ ****************************
                *****************************************************************************************
                
                """);
    }
}
