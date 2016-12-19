package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import clases.Persona;

@ManagedBean(name="dataTableBean")
@RequestScoped
public class DataTableBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -348349142399471076L;
	private  Persona persona = new Persona();
	private static List<Persona> personas = new ArrayList<Persona>();
	
	public void agregarPersona(){
		DataTableBean.personas.add(persona);
	}
	
	public void eliminarPersona(Persona persona) {
		DataTableBean.personas.remove(persona);
	}
	
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public List<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(List<Persona> personas) {
		DataTableBean.personas = personas;
	}
	
	
	
	
}
