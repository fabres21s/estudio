package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;



@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */
	@Override
	protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		System.out.println("recibiendo "+request.getParameter("x"));
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
		}

		ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		
		JSONObject responseJSON = new JSONObject();
		JSONArray json = new JSONArray();
		

		try {
			List<FileItem> items = uploadHandler.parseRequest(request);
			for (FileItem item : items) {
				if (!item.isFormField()) {
					
					
					Long nano = System.nanoTime();
					JSONObject jsono = new JSONObject();
					jsono.put("name", item.getName());
					jsono.put("size", item.getSize());
					jsono.put("url", "http://localhost:8080/fileupload/GestorImagenServlet?id=" + nano);
					jsono.put("thumbnail_url", "http://localhost:8080/fileupload/GestorImagenServlet?id=" + nano);
					jsono.put("delete_url", "upload?delfile=" + item.getName());
					jsono.put("delete_type", "GET");
					
					//PersonaBean.getInstance().setTotal
					
					ImagenUtil.getInstance().getMapImagenes().put(nano, item);
//					FileOutputStream fos = new FileOutputStream(request.getRealPath(".");
//					fos.write(b);
//					if (request.getParameter("target").equals("revision")) {
//						Utils.getInstance().addAdjuntosRevision(item.getName(), item.get());
//					} else if (request.getParameter("target").equals("task")) {
//						Utils.getInstance().addAdjuntosTarea(item.getName(), item.get());
//					}
					json.put(jsono);
				}
			}
			
			responseJSON.put("files", json);
		} catch (Exception e) {
			JSONObject jsono = new JSONObject();
			//jsono.put("error", "Error: "+e.getMessage());
			json.put(jsono);
			throw new RuntimeException(e);
		} finally {
			
			writer.write(responseJSON.toString());
			writer.close();
		}



	}


}
