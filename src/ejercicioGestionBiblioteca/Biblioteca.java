package ejercicioGestionBiblioteca;

import java.io.ObjectInputStream;
import java.util.ArrayList;

class Biblioteca {
	private ArrayList<Libro> biblioteca;

	Biblioteca() {
		this.biblioteca = cargarLibros(/*Aquí iría el ObjectInputStream que no sé como implementar*/);
	}
	
	void mostrarLibros(){
		for (Libro libro : biblioteca) {
			System.out.println(libro);
		}
	}

	ArrayList<Libro> cargarLibros(ObjectInputStream ois){
		ArrayList<Libro> carga = new ArrayList<Libro>();
		// La forma en la que se cargan los libros iria aquí 
		return carga;
	}

	void guardarLibros(Libro libro){
		this.biblioteca.add(libro);
	}
	

}