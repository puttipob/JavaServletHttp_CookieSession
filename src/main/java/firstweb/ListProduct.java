package firstweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ProductDAO dao = new ProductDAO();
			ArrayList<Product> list = dao.getAllProduct();

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<table border=1>");
			for (Product p : list) {
				out.println("<tr><td>" + p.getProductName() + "<td>");
				out.println("<td>" + p.getProductDetail() + "</td>");
				out.println("<td>" + p.getPrice() + "</td>");
				out.println("<td><img src='img/" + p.getPid() + ".jpg' width=150</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
