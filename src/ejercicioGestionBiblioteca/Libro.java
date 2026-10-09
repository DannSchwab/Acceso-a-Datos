package ejercicioGestionBiblioteca;

import java.io.Serializable;

class Libro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String titulo;
	private String autor;
	private float precio;
	private boolean existe;

	Libro(int id, String titulo, String autor, float precio) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
		this.existe = true;
	}

	Libro(int id, String titulo, float precio) {
		this.id = id;
		this.titulo = titulo;
		this.autor = "anonimo";
		this.precio = precio;
		this.existe = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public boolean isExiste() {
		return existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	@Override
	public String toString() {
		String libro = ("id: " + id + " || Libro: " + titulo + " || Autor: " + autor + " || precio: " + precio + " ||");
		return libro;

	}
}