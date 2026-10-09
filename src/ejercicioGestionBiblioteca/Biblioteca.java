package ejercicioGestionBiblioteca;

import java.io.*;
import java.util.ArrayList;

class Biblioteca {

	final String FICHERO = "";
	private ArrayList<Libro> biblioteca;

	Biblioteca() {
		this.biblioteca = cargarLibros(FICHERO);
	}
	
	void mostrarLibros(){
		for (Libro libro : biblioteca) {
			System.out.println(libro);
		}
	}

	ArrayList<Libro> cargarLibros(String a){
		ArrayList<Libro> carga = new ArrayList<Libro>();
		try(ObjectOutputStream escribir = new ObjectOutputStream()){
			
		}catch(IOException e) {
			
		}
		return carga;
	}

	void guardarLibros(Libro libro){
		cargarLibros(FICHERO);
		this.biblioteca.add(libro);
	}
	

}