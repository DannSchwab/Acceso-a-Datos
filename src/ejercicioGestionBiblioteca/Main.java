package ejercicioGestionBiblioteca;

import java.util.Scanner;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

public class Main {

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
				System.out.println("Introduzca un id valido");
				
				break;

			case 2:
				System.out.println("Introduzca el título e id");
				System.out.print("id: ");
				int id = sc.nextInt();
				sc.nextInt();
				System.out.println("");
				System.out.print("titulo: ");
				String titulo = sc.nextLine();

				buscarLibro(id, titulo);
				break;

			case 3:
				System.out.println("");
				break;

			}

		}

		public static void buscarLibro(int id, String titulo) {

		}
	}
