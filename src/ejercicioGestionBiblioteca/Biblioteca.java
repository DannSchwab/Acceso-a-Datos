package ejercicioGestionBiblioteca;

import java.util.ArrayList;

class Biblioteca {
	private ArrayList<Libro> biblioteca;

	Biblioteca() {
		this.biblioteca = new ArrayList<Libro>();
	}
	
	void cargarLibros(){
		for (Libro libro : biblioteca) {
			System.out.println(libro);
		}
	}

	void guardarLibros(Libro libro){
		
	}
	

}