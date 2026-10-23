package ejercicioPruebaExamen;

import java.io.Serializable;

/**
 * Contacto de la agenda. Se guarda con serialización dentro de la lista completa.
 */
public class Contacto implements Serializable {

	// CAMBIO: "número de versión" de la clase. Sin él, si añades un atributo,
	// Java calcula otro número y al cargar un contactos.dat antiguo salta
	// InvalidClassException (pregunta d del enunciado).
	private static final long serialVersionUID = 1L;

	private int id;
	private String nombre;
	// CAMBIO: String en vez de long. Un teléfono no es un número con el que
	// hagas cuentas: con long, "000" se guarda como 0 y no admite "+34 ...".
	private String telefono;
	// CAMBIO: se llama como en el enunciado (R3) y tiene el significado directo.
	private boolean eliminado;

	public Contacto(int id, String nombre, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.eliminado = false; // un contacto nuevo nunca está eliminado (2.1)
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	// CAMBIO: formato exacto de R5 -> "Contacto 1: Ana López · 600111222"
	@Override
	public String toString() {
		return "Contacto " + id + ": " + nombre + " · " + telefono;
	}
}