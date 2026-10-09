package ejercicioGestionBiblioteca;

import java.io.*;
import java.util.ArrayList;

class Biblioteca {

	static final String FICHERO = "libros.dat";
	private ArrayList<Libro> biblioteca;

	Biblioteca() {
		this.biblioteca = cargarLibros();
	}

	void guardarLibros() {
		try (ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream(FICHERO))){
			escribir.writeObject(biblioteca);
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	Libro consultarLibro(int id){
		for(Libro libro : biblioteca) {
			if (id == libro.getId()) {
				return libro;
			}
		}
		return null;
	}
	
	void agregarLibro(Libro libro){
		if(consultarLibro(libro.getId()) == null) {
			biblioteca.add(libro);
			guardarLibros();
			System.out.println("Libro añadido");
		}else {
			System.out.println("El libro con el ID " + libro.getId() + " ya existe y no se puede agregar");			
		}
	}
	
	ArrayList<Libro> cargarLibros(){		
		ArrayList<Libro> carga = new ArrayList<Libro>();
		if (!new File(FICHERO).exists()) {
		    return new ArrayList<Libro>();
		}
		try(ObjectInputStream escribir = new ObjectInputStream(new FileInputStream(FICHERO))){	
			carga = (ArrayList<Libro>)escribir.readObject();
			} catch (ClassNotFoundException e) {					
				e.printStackTrace();
			}catch(IOException ex){
				ex.printStackTrace();
			}
			
			return carga;			
		}

	void mostrarLibros() {
		for (Libro libro : biblioteca) {
			System.out.println(libro);
		}
	}

}