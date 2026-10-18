package ejercicio_registro_estudiantes;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class ProyectoResgistroEstudiantes {
	
	static Scanner sc = new Scanner(System.in);
	static final String FICHERO = "estudiantes.dat";
	static final int LONG_NOMBRE = 20;
	static final int TAM_REGISTRO = 4 + LONG_NOMBRE * 2 + 4; // 48 bytes

	public static void mostrar(String string) {
		System.out.println(string);
	}
	
	public static void escribir(RandomAccessFile raf, int id, String nombre, float nota) throws IOException {
		// Seek indica 
		raf.seek((long) (id - 1) * TAM_REGISTRO);
		// Escribir el ID (4 bytes)
		raf.writeInt(id);
		
		// Ajustar el nombre a 20 caracteres con StringBuilder
		StringBuilder sb = new StringBuilder(nombre);
		while (sb.length() < LONG_NOMBRE) {
			sb.append(' ');           // rellenar con espacios
		}
		sb.setLength(LONG_NOMBRE);    // recortar si era más largo
		
		// Escribir los caracteres uno a uno (20 x 2 bytes = 40 bytes)
		for (int i = 0; i < LONG_NOMBRE; i++) {
			raf.writeChar(sb.charAt(i));
		}
		
		// Escribir la nota (4 bytes)
		raf.writeFloat(nota);

	
	}
	
	public static void leer(RandomAccessFile raf, int id) throws IOException {
		long pos = (long) (id - 1) * TAM_REGISTRO;
 
		// Comprobar que el registro existe dentro del fichero
		if (id < 1 || pos + TAM_REGISTRO > raf.length()) {
			System.out.println("Registro no encontrado.");
			return;
		}
 
		raf.seek(pos);
 
		int idLeido = raf.readInt();
 
		// Si el hueco nunca se escribió, el ID almacenado no coincide
		if (idLeido != id) {
			System.out.println("Registro no encontrado.");
			return;
		}
 
		char[] letras = new char[LONG_NOMBRE];
		for (int i = 0; i < LONG_NOMBRE; i++) {
			letras[i] = raf.readChar();
		}
		String nombre = new String(letras).trim();
 
		float nota = raf.readFloat();
 
		System.out.println("El estudiante con ID " + idLeido + ", de nombre " + nombre
				+ " tiene como calificación " + nota);
	}
	
	// Devuelve true si el usuario quiere terminar
	private static boolean preguntarSiSalir() {
		System.out.println("Desea hacer algo más?");
		System.out.println("1- Si");
		System.out.println("2- No");
		int opcion = sc.nextInt();
		if (opcion == 2) {
			System.out.println("ADIOS");
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println("=========================================");
		System.out.println("========= REGISTRO ESTUDIANTES ==========");
		System.out.println("=========================================");
 
		boolean salir = false;
 
		while (!salir) {
			System.out.println("¿Qué necesita?");
			System.out.println("1- Escribir registro");
			System.out.println("2- Leer registro");
			System.out.println("0- Salir");
			System.out.println("=========================================");
 
			int opcion = sc.nextInt();
 
			switch (opcion) {
			case 1:
				try (RandomAccessFile raf = new RandomAccessFile(FICHERO, "rw")) {
					System.out.print("ID estudiante: ");
					int id = sc.nextInt();
					sc.nextLine(); // limpiar el buffer
					System.out.print("Nombre: ");
					String nombre = sc.nextLine();
					System.out.print("Nota: ");
					float nota = sc.nextFloat();
 
					if (id < 1) {
						System.out.println("El ID debe ser mayor que 0");
					} else {
						escribir(raf, id, nombre, nota);
						System.out.println("Registro guardado.");
					}
				} catch (IOException e) {
					System.out.println("Error de E/S: " + e.getMessage());
				}
				salir = preguntarSiSalir();
				break;
			case 2:
				try (RandomAccessFile raf = new RandomAccessFile(FICHERO, "r")) {
					System.out.print("ID del estudiante a leer: ");
					int id = sc.nextInt();
					leer(raf, id);
				} catch (IOException e) {
					System.out.println("Error de E/S: " + e.getMessage());
				}
				salir = preguntarSiSalir();
				break;
			case 0:
				System.out.println("ADIOS");
				salir = true;
				break;
			default:
				System.out.println("Opción no válida");
				break;
			}
		}
	}
 
}

