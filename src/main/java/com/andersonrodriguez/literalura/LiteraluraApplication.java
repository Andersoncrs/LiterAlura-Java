package com.andersonrodriguez.literalura;

import com.andersonrodriguez.literalura.service.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
				}
			}

		}
	}
}
