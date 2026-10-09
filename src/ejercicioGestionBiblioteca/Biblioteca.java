package ejercicioGestionBiblioteca;

import java.util.ArrayList;

class Biblioteca {
	
	final static String RUTA = "" ;
	
	private ArrayList<Libro> biblioteca;

	Biblioteca() {
		this.biblioteca = cargarLibros(RUTA);
	}
	
	void mostrarLibros(){
		for (Libro libro : biblioteca) {
			System.out.println(libro);
		}
	}

	ArrayList<Libro> cargarLibros(String a){
		ArrayList<Libro> carga = new ArrayList<Libro>();
		// La forma en la que se cargan los libros iria aquí 
		return carga;
	}

	void guardarLibros(Libro libro){
		this.biblioteca.add(libro);
	}
	

}