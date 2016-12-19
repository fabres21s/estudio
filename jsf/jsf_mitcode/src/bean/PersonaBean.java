package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="personaBean")
@RequestScoped
public class PersonaBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5147281444092208916L;
	private String nombre;
	private String mensaje;
	
	
	public void saludar () {
		this.mensaje = "Bienvenido "+this.nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
