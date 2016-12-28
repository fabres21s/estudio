package bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


@ManagedBean(name="personaBean")
@RequestScoped
public class PersonaBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5147281444092208916L;
	
	private String nombre;
	private String apellido = "a";
	private String mensaje;
	private boolean validate = false;
	private boolean check = false;
	private String texto = "Sin checkear";
	
	private UploadedFile file;
	
	private String fecha = "Sin asignar";
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void subir() {
		if (file != null) {
			System.out.println("Nombre del archivo "+file.getFileName());
		}
	}
	
	 public void handleFileUpload(FileUploadEvent event) {
	       System.out.println(event.getFile().getFileName());
	    }
	
	public void saludar () {
		this.mensaje = "Bienvenido "+this.nombre + " "+this.apellido;
		
		validate = this.nombre.contains("a");
		fecha = sdf.format(new Date());
	}
	
	public void verfecha() {
		this.mensaje ="hoy es "+ sdf.format(new Date());
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public boolean getValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public boolean getCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		if (check) {
			texto = "checkeado";
		} else {
			texto = "no checkeado";
		}
		this.check = check;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
}
