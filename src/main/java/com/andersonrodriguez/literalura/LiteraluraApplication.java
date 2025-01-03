package com.andersonrodriguez.literalura;

import com.andersonrodriguez.literalura.model.Autor;
import com.andersonrodriguez.literalura.model.Idioma;
import com.andersonrodriguez.literalura.model.Libro;
import com.andersonrodriguez.literalura.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Autowired
    private LibroService libroService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private IdiomaService idiomaService;

    @Override
    public void run(String... args) throws Exception {
        Menu menu = new Menu();

        menu.mostrarBienvenida();

        cicloPrincipal:
        while (true) {
            int opcionIngresada = menu.mostrarMenuPrincipal();
            switch (opcionIngresada) {
                case 1 -> {
                    String busquedaTitulo = menu.mostrarMenuBusquedaTitulo();
                    String json = new ConsumoApi(busquedaTitulo).ObtenerJsonBusqueda();
                    DatosAPi datosAPi = new ConvierteDatos().obtenerDatos(json, DatosAPi.class);
                    if (datosAPi.comprobarExistenciaResultados()) {
                        menu.mostrarResultadoNoEncontrado(busquedaTitulo);
                        continue;
                    }
                    List<DatosLibro> datosLibroList = datosAPi.resultados();
                    if (datosLibroList.size() > 20) {
                        datosLibroList = datosLibroList.subList(0, 20);
                    }

                    int opcionLibro = menu.mostrarLibrosEncontrados(datosLibroList);
                    if (opcionLibro == 0) {
                        continue;
                    }
                    DatosLibro datosLibro = datosLibroList.get(opcionLibro - 1);
                    Libro libro = new Libro(
                            datosLibro.titulo(),
                            datosLibro.descargas(),
                            datosLibro.autores(),
                            datosLibro.idiomas()
                    );
                    libro.setAutorList(autorService.comprobarExistenciaAutores(libro));
                    libro.setIdiomaList(idiomaService.comprobarExistenciaIdiomas(libro));
                    if (libroService.comprobarExistenciaLibro(libro)) {
                        menu.mostrarExistenciaLibro(libro);
                        continue;
                    }
                    libroService.AgregarLibro(libro);
                    menu.mostrarMensajeLibroGuardado(libro);
                }
                case 2 -> {
                    List<Libro> librosEnLaBaseDeDatos = libroService.listarTodosLosLibros();
                    if (librosEnLaBaseDeDatos.isEmpty()) {
                        menu.mostrarListaVacia("Libro");
                        continue;
                    }
                    menu.mostrarLibros(librosEnLaBaseDeDatos);
                }
                case 3 -> {
                    List<Autor> autoresEnLaBaseDeDatos = autorService.listarTodosLosAutores();
                    if (autoresEnLaBaseDeDatos.isEmpty()) {
                        menu.mostrarListaVacia("Autor");
                        continue;
                    }
                    menu.mostrarAutores(autoresEnLaBaseDeDatos);
                }
                case 4 -> {
                    int fechaIngresada = menu.solicitarFechaIngreso();
                    List<Autor> autorList = autorService.BuscarAutoresVivosPorFecha(fechaIngresada);
                    if (autorList.isEmpty()) {
                        menu.mostrarFechaNoEncontrada(fechaIngresada);
                        continue;
                    }
                    menu.mostrarAutores(autorList);
                }
                case 5 -> {
                    List<Idioma> idiomasDisponibles = idiomaService.listarIdiomasDisponibles();
                    if (idiomasDisponibles.isEmpty()) {
                        menu.mostrarListaVacia("Idioma");
                        continue;
                    }
                    int idiomaSeleccionado = menu.mostrarIdiomasDisponibles(idiomasDisponibles);
                    if (idiomaSeleccionado == 0) {
                        continue;
                    }
                    menu.mostrarLibros(idiomasDisponibles.get(idiomaSeleccionado - 1).getLibroList());
                }
                case 0 -> {
                    menu.MostrarDespedida();
                    break cicloPrincipal;
                }
            }

        }
    }
}
