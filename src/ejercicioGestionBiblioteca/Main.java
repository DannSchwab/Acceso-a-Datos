package ejercicioGestionBiblioteca;

import java.util.Scanner;

public class Main{

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		Biblioteca miBiblioteca = new Biblioteca();

		System.out.println("========================================");
		System.out.println("====== Bienvenido a la Biblioteca ======");
		System.out.println("========================================");
		System.out.println("====== -1 Guardar libro ================");
		System.out.println("====== -2 Buscar libro  ================");
		System.out.println("====== -3 Salir         ================");
		System.out.println("========================================");

		int opcion = sc.nextInt();

		switch (opcion) {

		case 1:
			int contador = 0;
			int id = 0;
			boolean valido = false;
			while (!valido) {
				System.out.print("Introduzca un id valido");
				id = sc.nextInt();
				for (Libro libro : miBiblioteca.cargarLibros(null)) {
					if (id == libro.getId()) {
						contador++;
					}
				}
				if (contador == 0) {
					valido = true;
				}
			}
			sc.nextLine();
			System.out.println("");
			System.out.print("Introduzca un titulo: ");
			String titulo = sc.nextLine();
			System.out.println("");
			System.out.print("Introdzuca un autor: ");
			String autor = sc.nextLine();
			System.out.println("");
			System.out.print("Introdzuca el precio: ");
			float precio = sc.nextFloat();
			if (autor == ""){
				
			}			
			Libro libro = new Libro(id, titulo, autor, precio);
			
			miBiblioteca.guardarLibros(libro);
			break;

		case 2:
			System.out.println("Introduzca el título e id");
			System.out.print("id: ");
			int id2 = sc.nextInt();
			System.out.println("");
			System.out.print("titulo: ");
			sc.nextLine();
			String titulo2 = sc.nextLine();

			buscarLibro(id2, titulo2);
			break;

		case 3:
			System.out.println("");
			break;

		}

	}

	public static void buscarLibro(int id, String titulo) {
		//Biblioteca.cargarLibros();
	}
}
