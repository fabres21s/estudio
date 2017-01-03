package servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;



public class GestorImagenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void dispatch(HttpServletRequest request, HttpServletResponse response, String nextPage) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) {
		try {

				FileItem item = ImagenUtil.getInstance().getMapImagenes().get(Long.valueOf(request.getParameter("id")));
				if (item != null) {
					response.setHeader("Content-Type", "image/jpg");
					response.setHeader("Content-Length", String.valueOf(item.get().length));
					response.setHeader("Content-Disposition", "inline; filename="+item.getName());
					
					OutputStream os = response.getOutputStream();
					os.write(item.get());
					os.close();
				}

			

		} catch (Exception e) {
			//logger.error("Error al enviar imagen", e);
		}
	}

}