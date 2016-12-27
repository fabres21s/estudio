package bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class FileUploadManagedBean {
	private UploadedFile file;
	private List<UploadedFile> files = new ArrayList<UploadedFile>();
	
	@PostConstruct
	public void init() {
	}
	
	

	
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void subir() {
		
	}
	public void delete(UploadedFile file) {
		files.remove(file);
	}
	
	public void fileUploadListener(FileUploadEvent e){
		// Get uploaded file from the FileUploadEvent
		this.file = e.getFile();
		// Print out the information of the file
		System.out.println("Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize());
		files.add(file);
		System.out.println("FileUploadManagedBean.fileUploadListener() ::: "+files.size());
	}

	public List<UploadedFile> getFiles() {
		System.out.println("FileUploadManagedBean.getFiles() ::: "+files.size());
		return files;
	}

	public void setFiles(List<UploadedFile> files) {
		this.files = files;
	}
}