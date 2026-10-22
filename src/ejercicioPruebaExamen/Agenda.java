package ejercicioPruebaExamen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;




public class Agenda {
	
	static final String FICHERO = "agenda.dat";

	private ArrayList<Contacto> agenda;
	
	Agenda(){
		this.agenda = cargarAgenda();
	}
	
	
	
	public ArrayList<Contacto> getAgenda() {
		return agenda;
	}



	public void setAgenda(ArrayList<Contacto> agenda) {
		this.agenda = agenda;
	}



	public ArrayList<Contacto> cargarAgenda(){
		ArrayList<Contacto> carga = new ArrayList<Contacto>();
		if (!new File(FICHERO).exists()) {
			return new ArrayList<Contacto>();
		}
		try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream(FICHERO))) {
			carga = (ArrayList<Contacto>) lector.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return carga;
	}
	
	void mostrarContactos() {
		for (Contacto contacto : agenda) {
			if (contacto.isExiste()) {
				System.out.println(contacto);
			}
		}
	}
	
	Contacto consultarContacto(int id) {
		for (Contacto contacto : agenda) {
			if (id == contacto.getId()) {
				return contacto;
			}
		}
		return null;
	}
	
	void agregarContacto(Contacto contacto) {
		if (consultarContacto(contacto.getId()) == null) {
			agenda.add(contacto);
			guardarContactos();
			System.out.println("Libro añadido");
		} else {
			System.out.println("El libro con el ID " + contacto.getId() + " ya existe y no se puede agregar");
		}
	}
	
	void guardarContactos() {
		try (ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream(FICHERO))) {
			escribir.writeObject(agenda);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	void borrarContacto(Contacto contacto) {
		for (int i = 0; i < this.agenda.size(); i++) {
			if (agenda.get(i) == contacto) {
				agenda.get(i).setExiste(false);
				;
			}
		}
		guardarContactos();
	}
}
