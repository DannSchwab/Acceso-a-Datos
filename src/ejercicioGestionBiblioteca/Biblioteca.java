package ejercicioGestionBiblioteca;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

class Biblioteca {

	Scanner sc = new Scanner(System.in);
	private final String FICHERO;
	private ArrayList<Libro> biblioteca;

	Biblioteca(String nombre) {
		this.FICHERO = "Biblioteca" + nombre + ".dat";
		this.biblioteca = cargarLibros();
	}

	private void guardarLibros() {
		try (ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream(FICHERO))) {
			escribir.writeObject(biblioteca);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	Libro consultarLibro(int id) {
		for (Libro libro : biblioteca) {
			if (id == libro.getId()) {
				return libro;
			}
		}
		return null;
	}

	void agregarLibro(Libro libro) {
		if (consultarLibro(libro.getId()) == null) {
			biblioteca.add(libro);
			guardarLibros();
			System.out.println("Libro añadido");
		} else {
			System.out.println("El libro con el ID " + libro.getId() + " ya existe y no se puede agregar");
		}
	}

	@SuppressWarnings("unchecked")
	private ArrayList<Libro> cargarLibros() {
		ArrayList<Libro> carga = new ArrayList<Libro>();
		if (!new File(FICHERO).exists()) {
			return new ArrayList<Libro>();
		}
		try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream(FICHERO))) {
			carga = (ArrayList<Libro>) lector.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return carga;
	}

	void mostrarLibros() {
		for (Libro libro : biblioteca) {
			if (libro.isExiste()) {
				System.out.println(libro);
			}
		}
	}

	void modificarLibro(Libro libro1, int opcion) {
		int nId;
		boolean continuar = true;
		for (int i = 0; i < this.biblioteca.size(); i++) {
			if (this.biblioteca.get(i) == libro1) {
				switch (opcion) {
				case 1:
					do {
						System.out.println("Introduzca un nuevo id");
						nId = sc.nextInt();
						if (consultarLibro(nId) == null) {
							this.biblioteca.get(i).setId(nId);
							continuar = false;
						} else {
							System.out.println("id ya existente");
							continuar = true;
						}
					} while (continuar);
					break;
				case 2:
					System.out.println("Introduzca un nuevo titulo:");
					this.biblioteca.get(i).setTitulo(sc.nextLine());
					break;
				case 3:
					System.out.println("Introduzca un nuevo autor:");
					this.biblioteca.get(i).setAutor(sc.nextLine());
					break;
				case 4:
					System.out.println("Introduzca un nuevo precio:");
					this.biblioteca.get(i).setPrecio(sc.nextFloat());
					break;
				case 5:
					boolean tE = this.biblioteca.get(i).isExiste();
					System.out.println("Existencia del libro marcada como" + tE);
					System.out.println("Desea cambiarlo?");
					System.out.println("y / n");
					String respuesta = sc.nextLine().trim().toLowerCase();
					if (respuesta.equals("y")) {
						if (tE == true) {
							this.biblioteca.get(i).setExiste(false);
						} else {
							this.biblioteca.get(i).setExiste(true);
						}
					} else {
						break;
					}
					break;
				}

			}
		}
		guardarLibros();
	}

	void eliminarLibro(Libro libro1) {
		for (int i = 0; i < this.biblioteca.size(); i++) {
			if (biblioteca.get(i) == libro1) {
				biblioteca.get(i).setExiste(false);
				;
			}
		}
		guardarLibros();
	}

}