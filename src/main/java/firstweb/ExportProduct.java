package firstweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ExportProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// XML
		/*
		 * response.setContentType("text/xml; charset=Utf-8");
		 */
		// Export File
		/*
		 * response.setHeader("Content-Disposition", "attachment;filename=product.csv");
		 */
		response.setContentType("application/json; charset=Utf-8");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();

		ProductDAO dao;
		try {
			dao = new ProductDAO();
			ArrayList<Product> list = dao.getAllProduct();
			String json = mapper.writeValueAsString(list);
			out.print(json);

			// XML
			/*
			 * out.println("<products>"); for (Product p : list) { out.println("<product>");
			 * out.println("<name>" + p.getProductName() + "</name>");
			 * out.println("</product>"); } out.println("</products>");
			 */
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
