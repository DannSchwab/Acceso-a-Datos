package ejercicioGestionBiblioteca;

import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		Biblioteca miBiblioteca = new Biblioteca();
		boolean seguir = true;
		
		while(seguir) {
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
				int id = 0;
				System.out.print("Introduzca un id valido");
				id = sc.nextInt();
				while (miBiblioteca.consultarLibro(id) != null) {
					System.out.println("Este id ya existe");
					System.out.print("Introduzca un id valido");
					id = sc.nextInt();
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
				if (autor.isEmpty()){
					Libro libro = new Libro(id, titulo, precio);				
					miBiblioteca.agregarLibro(libro);
				}else {
					Libro libro = new Libro(id, titulo, autor, precio);				
					miBiblioteca.agregarLibro(libro);
				}
				
				break;
				
			case 2:
				System.out.print("Introduzca el id del libro: ");
				int id2 = sc.nextInt();
				System.out.println("");
				Libro buscar = miBiblioteca.consultarLibro(id2);
				if(buscar == null) {
					System.out.println("No existe ningún libro con ese id"); 								
				}else {
					System.out.println(buscar); 				
				}
				break;
				
			case 3:
				System.out.println("Adios");
				seguir = false;
				break;
			}	
		}
	}
}
