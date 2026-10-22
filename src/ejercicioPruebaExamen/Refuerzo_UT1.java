/*
 * =====================================================================
 *  EJERCICIO DE REFUERZO · UT1 TRABAJO CON FICHEROS · 2º DAM
 *  Acceso a Datos · Colegio Santa Gema Galgani        (autoevaluación: 5 puntos)
 * =====================================================================
 *  Este ejercicio se plantea igual que la prueba práctica, pero con otros
 *  datos:
 *    - Parte 1. Inventario de productos con RandomAccessFile (como el
 *      Registro de estudiantes).
 *    - Parte 2. Agenda de contactos con serialización (como la Gestión de
 *      biblioteca).
 *    - Parte 3. Programa completo con menú.
 *
 *  INSTRUCCIONES
 *  - Crea un proyecto Java en Eclipse. Tú decides cómo organizar el código:
 *    qué clases crear, qué métodos, con qué nombres y parámetros.
 *  - Respeta los REQUISITOS FIJOS de abajo: son los mismos tipos de requisitos
 *    que tendrás en el examen.
 *  - Al final tienes una BATERÍA DE PRUEBAS con los resultados que debes
 *    obtener. Úsala para comprobar tu solución.
 *  - Consejo: intenta hacerlo sin mirar los proyectos de clase. Si te atascas,
 *    repasa el informe de indagación (streams, buffer, serialización).
 *
 * =====================================================================
 *  REQUISITOS FIJOS (obligatorios)
 * =====================================================================
 *  R1. Productos: fichero "productos.dat", gestionado con RandomAccessFile.
 *      Registro de longitud fija con estos campos, en este orden:
 *        - codigo -> int
 *        - nombre -> exactamente 25 caracteres, escritos con writeChar()
 *        - precio -> double  (¡OJO! un double ocupa 8 bytes, no 4)
 *      El registro de cada producto empieza en la posición
 *      codigo * TAM_REGISTRO, donde TAM_REGISTRO es el tamaño del registro en
 *      bytes (calcúlalo tú).
 *  R2. Contactos: fichero "contactos.dat". La lista COMPLETA de contactos se
 *      guarda como un único objeto con serialización y se carga entera al
 *      arrancar.
 *  R3. Cada contacto tiene: id (int), nombre, teléfono y un booleano eliminado.
 *  R4. Menú por consola que se repite hasta elegir 0, con estas opciones y
 *      este mismo número:
 *        1. Escribir producto
 *        2. Leer producto
 *        3. Actualizar precio
 *        4. Mostrar todos los productos
 *        5. Agregar contacto
 *        6. Listar contactos
 *        7. Eliminar contacto
 *        0. Salir
 *  R5. Los mensajes entre comillas de este enunciado deben aparecer tal cual.
 *  R6. Ni un error de fichero ni una letra tecleada donde se espera un número
 *      deben cerrar el programa.
 *
 * =====================================================================
 *  PARTE 1. INVENTARIO DE PRODUCTOS (ACCESO ALEATORIO) ......... 2,25 puntos
 * =====================================================================
 *  1.1 Escribir un producto ...................................... 0,75 puntos
 *      - Se piden código, nombre y precio, y se escribe el registro en su
 *        posición.
 *      - El nombre debe ocupar exactamente 25 caracteres: si es más largo se
 *        recorta y si es más corto se rellena con espacios (usa StringBuilder).
 *      - Después de escribir, muestra:
 *        "Producto guardado. Longitud del fichero: N bytes"
 *
 *  1.2 Leer un producto .......................................... 0,5 puntos
 *      - Se pide el código. Si el registro queda fuera del fichero, o es un
 *        hueco (el código leído es 0), muestra "Producto no encontrado."
 *      - Si existe, muestra: "Producto 2: Ratón inalámbrico · 12.99 €"
 *        (el nombre sin los espacios del final).
 *
 *  1.3 Actualizar el precio ...................................... 0,5 puntos
 *      - Se pide el código y el nuevo precio, y se sobrescribe SOLO el precio
 *        (¿cuántos bytes hay delante del precio dentro del registro?).
 *      - Si el registro queda fuera del fichero o es un hueco:
 *        "Producto no encontrado."
 *      - Si se actualiza: "Precio actualizado con éxito"
 *
 *  1.4 Mostrar todos los productos ............................... 0,5 puntos
 *      - Recorre el fichero completo de principio a fin y muestra cada producto
 *        con el formato de 1.2. Los huecos NO se muestran.
 *
 * =====================================================================
 *  PARTE 2. AGENDA DE CONTACTOS (SERIALIZACIÓN) ................ 2,25 puntos
 * =====================================================================
 *  2.1 Contacto serializable ..................................... 0,5 puntos
 *      - El contacto se puede serializar de forma controlada entre versiones.
 *      - Un contacto nuevo nunca está eliminado.
 *      - Se muestra así: "Contacto 1: Ana López · 600111222"
 *
 *  2.2 Cargar los contactos al arrancar .......................... 0,5 puntos
 *      - Si contactos.dat todavía no existe, el programa arranca sin error y
 *        con la agenda vacía.
 *      - Los recursos se cierran siempre y se tratan TODAS las excepciones
 *        comprobadas de la lectura (¡son dos!).
 *
 *  2.3 Guardar los contactos ..................................... 0,5 puntos
 *      - Tras cada cambio, la lista completa se vuelve a guardar en el fichero.
 *      - Al cerrar el programa y volver a abrirlo, los contactos siguen ahí.
 *
 *  2.4 Agregar un contacto ....................................... 0,35 puntos
 *      - Se piden id, nombre y teléfono.
 *      - Si se agrega: "Contacto agregado"
 *      - Si ya existe un contacto activo con ese id:
 *        "El contacto con el ID 1 ya existe"
 *
 *  2.5 Listar contactos .......................................... 0,2 puntos
 *      - Muestra los contactos que no estén eliminados.
 *      - Si no hay ninguno: "La agenda está vacía"
 *
 *  2.6 Eliminar un contacto (borrado lógico) ..................... 0,2 puntos
 *      - El contacto NO se quita de la lista: se marca como eliminado y se
 *        guarda el cambio.
 *      - Si se elimina: "Contacto eliminado"
 *      - Si no existe: "No se puede eliminar el contacto"
 *
 * =====================================================================
 *  PARTE 3. PROGRAMA COMPLETO .................................. 0,5 puntos
 * =====================================================================
 *  - Menú del requisito R4 funcionando ................................ 0,25
 *  - Excepciones tratadas y requisito R6 ............................... 0,15
 *  - Código organizado en clases y métodos con sentido, y comentado .... 0,10
 *
 * =====================================================================
 *  BATERÍA DE PRUEBAS (borra productos.dat y contactos.dat antes de empezar)
 * =====================================================================
 *   #  Opción y datos                                   Resultado esperado
 *   1  1 → 1, "Teclado", 25,5                           Longitud del fichero: 124 bytes
 *   2  1 → 2, "Ratón inalámbrico", 12,99                Longitud del fichero: 186 bytes
 *   3  1 → 5, "Monitor 27 pulgadas con altavoces", 199  Longitud del fichero: 372 bytes
 *   4  2 → 5                                            Producto 5: Monitor 27 pulgadas con a · 199.0 €
 *   5  2 → 3                                            Producto no encontrado.   (hueco)
 *   6  2 → 9                                            Producto no encontrado.   (fuera)
 *   7  3 → 2, 10,5                                      Precio actualizado con éxito
 *   8  3 → 4, 50                                        Producto no encontrado.   (hueco)
 *   9  4                                                Productos 1, 2 (10.5 €) y 5, sin huecos
 *  10  6                                                La agenda está vacía
 *  11  5 → 1, "Ana López", 600111222
 *      5 → 2, "Luis Pérez", 600333444                   Contacto agregado (dos veces)
 *  12  5 → 1, "Otro", 000                               El contacto con el ID 1 ya existe
 *  13  7 → 2   y después   7 → 8                       Contacto eliminado · No se puede eliminar el contacto
 *  14  0, vuelve a ejecutar y elige 6                   Contacto 1: Ana López · 600111222
 *  15  Teclear "x" en el menú                           Aviso, y el programa sigue
 *
 * =====================================================================
 *  PREGUNTAS PARA PENSAR (no puntúan, pero te preparan para el examen)
 * =====================================================================
 *  a) ¿Por qué tras escribir el producto 1 el fichero mide 124 bytes y no 62?
 *  b) ¿Qué hay guardado en las zonas de los productos 3 y 4?
 *  c) Si al actualizar el precio solo comprobaras la longitud del fichero,
 *     ¿qué pasaría en la prueba 8?
 *  d) ¿Qué ocurriría al guardar si la clase de los contactos no fuera
 *     serializable? ¿Y si le añades un atributo sin haber declarado
 *     serialVersionUID y luego cargas un contactos.dat antiguo?
 * =====================================================================
 */

package ejercicioPruebaExamen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Refuerzo_UT1 {

	static final String FICHERO = "productos.dat";
	static final int LONG_NOMBRE = 25;
	static final int TAM_REGISTRO = 4 + LONG_NOMBRE * 2 + 8;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		int opcion;
		boolean seguir = true;

		do {
			System.out.println("=========================================");
			System.out.println("===    1. Inventario de Productos     ===");
			System.out.println("===    2. Agenda de contactos         ===");
			System.out.println("===    3. Me rindo                    ===");
			System.out.println("=========================================");
			System.out.print("Elija una opcion: ");
			opcion = leerNumeroEnteroValido();
			switch (opcion) {

			case 1:
				manejarInventario();
				seguir = true;
				break;

			case 2:
				manejarAgendaContactos();
				seguir = true;
				break;

			case 3:
				System.out.println("Te entiendo macho");
				seguir = false;
				break;

			default:
				System.out.println("Opcion no válida");
				break;
			}
		} while (seguir);

		sc.close();
	}

	public static void manejarInventario() {

		boolean seguir = true;
		int codigo;
		String nombre;
		double precio;

		int opcion = 0;

		do {
			System.out.println("");
			System.out.println("1. Escribir producto");
			System.out.println("2. Leer producto");
			System.out.println("3. Actualizar precio");
			System.out.println("4. Mostrar todos los productos");
			System.out.println("5. Volver atrás");
			System.out.print("Elija una opción: ");
			opcion = leerNumeroEnteroValido();

			switch (opcion) {
			case 1:
				boolean valido = false;
				do {
					do {
						System.out.print("Introduzca un id valido: ");
						codigo = leerNumeroEnteroValido();
					} while (codigo <= 0);
					System.out.print("Introduzca el nombre del articulo: ");
					StringBuilder sb = new StringBuilder(sc.nextLine());
					while (sb.length() < LONG_NOMBRE) {
						sb.append(' '); // rellenar con espacios
					}
					sb.setLength(LONG_NOMBRE);

					nombre = sb.toString();
					System.out.print("Introduzca el precio del articulo: ");
					precio = leerNumeroDoubleValido();
					valido = escribirProducto(codigo, nombre, precio);
				} while (!valido);
				break;
			case 2:
				System.out.print("Introduzca el id del producto que quiera leer: ");
				codigo = leerNumeroEnteroValido();
				leerProducto(codigo);
				break;
			case 3:
				System.out.print("Introduzca el id del producto del que quiera actualizar el precio: ");
				codigo = leerNumeroEnteroValido();
				System.out.print("Introduzca el nuevo precio: ");
				precio = leerNumeroDoubleValido();
				modificarPrecio(codigo, precio);
				break;
			case 4:
				mostrarTodosLosProductos();
				break;
			case 5:
				seguir = false;
				break;
			default:
				System.out.println("opción no valida");
				break;
			}
		} while (seguir);
	}

	public static void manejarAgendaContactos() {

		int codigo = 0;
		String nombre;
		long numero;
		boolean seguir = false;

		Agenda miAgenda = new Agenda();

		int opcion = 0;

		do {
			System.out.println("");
			System.out.println("1. Agregar contacto");
			System.out.println("2. Listar contactos");
			System.out.println("3. Eliminar contacto");
			System.out.println("4. Volver atrás");
			System.out.print("Elija una opción: ");

			opcion = leerNumeroEnteroValido();

			switch (opcion) {
			case 1:
				boolean seguirA = false;
				while (!seguirA) {
					System.out.print("Introduzca un id de contacto valido: ");
					codigo = leerNumeroEnteroValido();
					int size = miAgenda.getAgenda().size();
					if (size != 0) {
						for (int i = 0; i < miAgenda.getAgenda().size(); i++) {
							int codigoLeido = miAgenda.getAgenda().get(i).getId();
							if (codigoLeido != codigo) {
								seguirA = true;
							}
						}
						if (!seguirA) {
							System.out.println("id ya existente, use otro.");
						}
					} else {
						seguirA = true;
					}
				}
				System.out.print("Introduzco el nombre del contacto: ");
				nombre = sc.nextLine();
				System.out.print("Introduzca el número del contacto: ");
				numero = leerNumeroLongValido();
				System.out.print("");
				Contacto contacto = new Contacto(codigo, nombre, numero);
				miAgenda.agregarContacto(contacto);
				break;
			case 2:
				miAgenda.mostrarContactos();
				break;
			case 3:
				boolean seguirE = false;
				while (!seguirE) {
					System.out.print("Introduzca un id de contacto valido: ");
					codigo = leerNumeroEnteroValido();
					int size = miAgenda.getAgenda().size();
					if (size != 0) {
						for (int i = 0; i < miAgenda.getAgenda().size(); i++) {
							int codigoLeido = miAgenda.getAgenda().get(i).getId();
							if (codigoLeido == codigo) {
								seguirE = true;
							}
						}
						if (!seguirE) {
							System.out.println("No existe un contacto con tal id");
						}
					} else {
						System.out.println("No existen contactos en la agenda que eliminar");
						break;
					}
				}

				Contacto eliminar = miAgenda.consultarContacto(codigo);
				miAgenda.borrarContacto(eliminar);
				System.out.println("Contacto Eliminado");

				break;
			case 4:
				miAgenda.mostrarContactos();
				break;
			default:
				System.out.println("Opcion no valida");
				break;
			}
		} while (opcion != 4);

	}

	public static int leerNumeroEnteroValido() {
		int numero = 0;
		boolean valido = false;
		while (!valido) {
			try {
				numero = sc.nextInt();
				sc.nextLine();
				valido = true;
			} catch (InputMismatchException e) {
				System.out.println("Escribe un número compadre");
				sc.nextLine();
				valido = false;
			}
		}
		return numero;
	}

	public static double leerNumeroDoubleValido() {
		double numero = 0;
		boolean valido = false;
		while (!valido) {
			try {
				numero = sc.nextDouble();
				sc.nextLine();
				valido = true;
			} catch (InputMismatchException e) {
				System.out.println("Escribe un número compadre");
				sc.nextLine();
				valido = false;
			}
		}
		return numero;
	}

	public static long leerNumeroLongValido() {
		long numero = 0;
		boolean valido = false;
		while (!valido) {
			try {
				numero = sc.nextLong();
				sc.nextLine();
				valido = true;
			} catch (InputMismatchException e) {
				System.out.println("Escribe un número compadre");
				sc.nextLine();
				valido = false;
			}
		}
		return numero;
	}

	public static boolean escribirProducto(int codigo, String nombre, double precio) {
		boolean valido;
		try (RandomAccessFile raf = new RandomAccessFile(FICHERO, "rw")) {
			raf.seek((long) codigo * TAM_REGISTRO);
			raf.writeInt(codigo);

			for (int i = 0; i < LONG_NOMBRE; i++) {
				raf.writeChar(nombre.charAt(i));
			}

			raf.writeDouble(precio);

			System.out.println("Producto guardado. Longitud del fichero: " + raf.length() + " bytes");
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
			e.printStackTrace();
			return valido = false;
		} catch (IOException e) {
			System.out.println("No se ha podido conectar con el fichero");
			e.printStackTrace();
			return valido = false;
		}
		return valido = true;
	}

	public static void leerProducto(int codigo) {

		try (RandomAccessFile raf = new RandomAccessFile(FICHERO, "r")) {

			long pos = ((long) codigo * TAM_REGISTRO);

			if (codigo < 1 || pos + TAM_REGISTRO > raf.length()) {
				System.out.println("Producto no encontrado.");
				return;
			}

			raf.seek(pos);

			int codigoLeido = raf.readInt();

			// Si el hueco nunca se escribió, el ID almacenado no coincide
			if (codigoLeido != codigo) {
				System.out.println("Producto no encontrado.");
				return;
			}

			char[] letras = new char[LONG_NOMBRE];
			for (int i = 0; i < LONG_NOMBRE; i++) {
				letras[i] = raf.readChar();
			}

			String nombre = new String(letras).trim();

			double precio = raf.readDouble();

			System.out.println("Producto " + codigoLeido + ": " + nombre + " · " + precio + " €");

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("No se ha podido conectar con el fichero");
			e.printStackTrace();
		}

	}

	public static void modificarPrecio(int codigo, double precio) {

		try (RandomAccessFile raf = new RandomAccessFile(FICHERO, "rw")) {
			long pos = ((long) codigo * TAM_REGISTRO);

			if (codigo < 1 || pos + TAM_REGISTRO > raf.length()) {
				System.out.println("Producto no encontrado.");
				return;
			}

			raf.seek(pos);
			int codigoLeido = raf.readInt();
			if (codigoLeido != codigo) {
				System.out.println("Producto no encontrado.");
				return;
			}

			long possPrecio = pos + (4 + LONG_NOMBRE * 2);
			raf.seek(possPrecio);

			raf.writeDouble(precio);

			System.out.println("Precio actualizado con éxito");

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("No se ha podido conectar con el fichero");
			e.printStackTrace();
		}

	}

	public static void mostrarTodosLosProductos() {

		try (RandomAccessFile raf = new RandomAccessFile(FICHERO, "r")) {
			int cantRegistros = (int) raf.length() / TAM_REGISTRO;

			for (int i = 1; i < cantRegistros; i++) {
				raf.seek(i * TAM_REGISTRO);
				int codigoLeido = raf.readInt();

				if (codigoLeido != 0) {
					char[] letras = new char[LONG_NOMBRE];
					for (int x = 0; x < LONG_NOMBRE; x++) {
						letras[x] = raf.readChar();
					}
					String nombre = new String(letras).trim();
					double precio = raf.readDouble();

					System.out.println("Producto " + codigoLeido + ": " + nombre + " · " + precio + " €");
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("No se ha podido conectar con el fichero");
			e.printStackTrace();
		}

	}
}
