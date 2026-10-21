package ejercicioGestionBiblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static Biblioteca biblioteca;

	public static void main(String[] args) {

		int id;
		int opcion;
		String nombre = "";
		boolean seguir = true;
		boolean seguir2 = true;

		ArrayList<Biblioteca> misBibliotecas = new ArrayList<Biblioteca>();
		Biblioteca biblioteca1 = new Biblioteca("Nacional");
		Biblioteca biblioteca2 = new Biblioteca("Provincial");
		Biblioteca biblioteca3 = new Biblioteca("Municipal");

		misBibliotecas.add(biblioteca1);
		misBibliotecas.add(biblioteca2);
		misBibliotecas.add(biblioteca3);

		while (seguir) {
			System.out.println("=================================================");
			System.out.println("====== Bienvenido al gestor de Bibliotecas ======");
			System.out.println("=================================================");
			System.out.println("======   Qué biblioteca desea consultar?   ======");
			System.out.println("=================================================");
			System.out.println("========= -1 Nacional      ======================");
			System.out.println("========= -2 Provincial    ======================");
			System.out.println("========= -3 Municipal     ======================");
			System.out.println("=================================================");

			while (nombre.equals("")) {
				opcion = sc.nextInt();
				switch (opcion) {
				case 1:
					nombre = "Nacional";
					biblioteca = misBibliotecas.get(0);
					break;
				case 2:
					nombre = "Provincial";
					biblioteca = misBibliotecas.get(1);
					break;
				case 3:
					nombre = "Municipal";
					biblioteca = misBibliotecas.get(2);
					break;
				default:
					System.out.println("Opcion no valida");
					break;
				}

			}

			while (seguir2) {

				System.out.println("");
				System.out.println("            Biblioteca " + nombre);
				System.out.println("=================================================");
				System.out.println("========= -1 Guardar libro   ====================");
				System.out.println("========= -2 Buscar libro    ====================");
				System.out.println("========= -3 Modificar Libro ====================");
				System.out.println("========= -4 Eliminar libro  ====================");
				System.out.println("========= -5 Crear copia     ====================");
				System.out.println("========= -6 Salir           ====================");
				System.out.println("=================================================");

				opcion = sc.nextInt();

				switch (opcion) {

				case 1:
					System.out.print("Introduzca un id valido: ");
					id = sc.nextInt();
					while (biblioteca.consultarLibro(id) != null) {
						System.out.println("Este id ya existe");
						System.out.print("Introduzca un id valido: ");
						id = sc.nextInt();
						System.out.println("");
					}
					sc.nextLine();
					System.out.println("");
					System.out.print("Introduzca un titulo: ");
					String titulo = sc.nextLine();
					System.out.println("");
					System.out.print("Introduzca un autor: ");
					String autor = sc.nextLine();
					System.out.println("");
					System.out.print("Introduzca el precio: ");
					float precio = sc.nextFloat();
					Libro libro;
					if (autor.isEmpty()) {
						libro = new Libro(id, titulo, precio);
					} else {
						libro = new Libro(id, titulo, autor, precio);
					}
					biblioteca.agregarLibro(libro);

					break;

				case 2:
					System.out.print("Introduzca el id del libro: ");
					id = sc.nextInt();
					System.out.println("");
					Libro buscar = biblioteca.consultarLibro(id);
					if (buscar == null) {
						System.out.println("No existe ningún libro con ese id");
					} else if (!buscar.isExiste()) {
						System.out.println("El libro con ese id no está en existencia");
					} else {
						System.out.println(buscar);
					}
					break;

				case 3:
					boolean modificacionHecha = false;
					while (!modificacionHecha) {
						System.out.print("Introduzca el id del libro que desea modificar: ");
						id = sc.nextInt();
						if (biblioteca.consultarLibro(id) == null) {
							System.out.print("No existe ningún libro con ese id");
						} else {
							System.out.print("El libro que desea modificar es: ");
							System.out.println(biblioteca.consultarLibro(id).toString());
							System.out.println("Desea continuar?");
							System.out.println("-1 si");
							System.out.println("-2 no");
							opcion = sc.nextInt();
							if (opcion == 2) {
								break;
							} else {
								System.out.println("Qué desea modificar?");
								System.out.println("-1 id");
								System.out.println("-2 titulo");
								System.out.println("-3 autor");
								System.out.println("-4 precio");
								System.out.println("-5 en existencia?");
								opcion = sc.nextInt();
								biblioteca.modificarLibro(biblioteca.consultarLibro(id), opcion);
								modificacionHecha = true;
							}
						}
					}
					break;

				case 4:
					System.out.print("Introduzca el id del libro que desea eliminar: ");
					id = sc.nextInt();
					System.out.println("");
					if (biblioteca.consultarLibro(id) != null) {
						System.out.println(biblioteca.consultarLibro(id).toString());
						biblioteca.eliminarLibro((biblioteca.consultarLibro(id)));
					} else {
						System.out.println("Id no encontrado");
					}
					break;
				case 5:
					
					Biblioteca copia = new Biblioteca(nombre + "CopiaSeguridad");
					copia.setBiblioteca(biblioteca.getBiblioteca());
					break;

				case 6:
					System.out.println("Adios");
					seguir = false;
					seguir2 = false;
					break;
				default:
					System.out.println("Opción no válida");
					break;
				}
			}
		}
	}

}
