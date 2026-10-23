package ejercicioPruebaExamen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Capa de datos de la agenda: guarda y carga la lista COMPLETA de contactos
 * como un único objeto serializado (R2). No escribe mensajes para el usuario:
 * devuelve resultados y es el menú quien decide qué mostrar.
 */
public class Agenda {

	// CAMBIO: nombre del fichero del enunciado (antes "agenda.dat")
	static final String FICHERO = "contactos.dat";

	private ArrayList<Contacto> contactos;

	public Agenda() {
		this.contactos = cargarContactos();
	}

	/**
	 * Carga la lista al arrancar. Si el fichero no existe, agenda vacía (2.2).
	 * Se tratan las dos excepciones comprobadas de readObject.
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<Contacto> cargarContactos() {
		if (!new File(FICHERO).exists()) {
			return new ArrayList<>();
		}
		try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream(FICHERO))) {
			return (ArrayList<Contacto>) lector.readObject();
		} catch (ClassNotFoundException e) {
			System.out.println("El fichero de contactos no tiene un formato válido");
		} catch (IOException e) {
			System.out.println("No se ha podido leer el fichero de contactos");
		}
		return new ArrayList<>();
	}

	/** Guarda la lista completa. Se llama tras cada cambio (2.3). */
	private void guardarContactos() {
		try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(FICHERO))) {
			escritor.writeObject(contactos);
		} catch (IOException e) {
			System.out.println("No se ha podido guardar el fichero de contactos");
		}
	}

	/**
	 * Devuelve el contacto ACTIVO con ese id, o null si no hay ninguno.
	 * CAMBIO: antes también devolvía los eliminados, así que no podías reutilizar
	 * el id de un contacto borrado y podías "eliminar" dos veces el mismo.
	 */
	public Contacto buscarActivo(int id) {
		for (Contacto c : contactos) {
			if (c.getId() == id && !c.isEliminado()) {
				return c; // en cuanto lo encuentra, sale: el último elemento no "pisa" la respuesta
			}
		}
		return null;
	}

	/**
	 * CAMBIO: devuelve boolean en vez de imprimir. true = agregado, false = ya existía.
	 * Así el menú no necesita ningún bucle para comprobar el id.
	 */
	public boolean agregarContacto(Contacto contacto) {
		if (buscarActivo(contacto.getId()) != null) {
			return false;
		}
		contactos.add(contacto);
		guardarContactos();
		return true;
	}

	/**
	 * Borrado lógico (2.6): se marca, no se quita de la lista.
	 * CAMBIO: recibe el id y devuelve si lo ha podido eliminar.
	 */
	public boolean eliminarContacto(int id) {
		Contacto c = buscarActivo(id);
		if (c == null) {
			return false;
		}
		c.setEliminado(true);
		guardarContactos();
		return true;
	}

	/** Devuelve solo los contactos no eliminados (2.5). */
	public ArrayList<Contacto> getContactosActivos() {
		ArrayList<Contacto> activos = new ArrayList<>();
		for (Contacto c : contactos) {
			if (!c.isEliminado()) {
				activos.add(c);
			}
		}
		return activos;
	}
}