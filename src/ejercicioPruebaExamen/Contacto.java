package ejercicioPruebaExamen;

import java.io.Serializable;

public class Contacto implements Serializable {

	private int id;
	private String nombre;
	private long num;
	private boolean existe;
	
	
	Contacto(int id, String nombre, long num){
		this.id = id;
		this.nombre = nombre;
		this.num = num;
		this.existe = true;
	}

	
	
	public boolean isExiste() {
		return existe;
	}



	public void setExiste(boolean existe) {
		this.existe = existe;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public long getNum() {
		return num;
	}



	public void setNum(long num) {
		this.num = num;
	}



	@Override
	public String toString() {
		return "Contacto" + id + ": " + nombre + "· " + num;
	}
	
	
}
