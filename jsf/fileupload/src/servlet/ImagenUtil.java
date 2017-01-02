package servlet;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

public class ImagenUtil {

	public static ImagenUtil instance;
	private Map<Long, FileItem> mapImagenes;
	
	public static ImagenUtil getInstance() {
		if (instance == null) {
			instance = new ImagenUtil();
		}
		return instance;
	}
	
	public ImagenUtil() {
		setMapImagenes(new HashMap<Long, FileItem>());
	}

	public Map<Long, FileItem> getMapImagenes() {
		return mapImagenes;
	}

	public void setMapImagenes(Map<Long, FileItem> mapImagenes) {
		this.mapImagenes = mapImagenes;
	}
}
