package com.andersonrodriguez.literalura;

import com.andersonrodriguez.literalura.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menu menu = new Menu();

		menu.mostrarBienvenida();

		cicloPrincipal:
		while(true){
			int opcionIngresada = menu.mostrarMenuPrincipal();
			switch (opcionIngresada){
				case 1 -> {
					String busquedaTitulo = menu.mostrarMenuBusquedaTitulo();
					String json = new ConsumoApi(busquedaTitulo).ObtenerJsonBusqueda();
					DatosAPi datosAPi = new ConvierteDatos().obtenerDatos(json, DatosAPi.class);
					if(datosAPi.comprobarExistenciaResultados()){
						menu.mostrarResultadoNoEncontrado(busquedaTitulo);
						continue cicloPrincipal;
					}
					List<DatosLibro> datosLibroList = datosAPi.resultados();
					if(datosLibroList.size() > 20){
						datosLibroList = datosLibroList.subList(0, 20);
					}

					int opcionLibro = menu.mostrarLibrosEncontrados(datosLibroList);
					if(opcionLibro == 0){
						continue cicloPrincipal;
					}
					System.out.println("Libro seleccionado: " + datosLibroList.get(opcionLibro - 1));
				}
			}

		}
	}
}
