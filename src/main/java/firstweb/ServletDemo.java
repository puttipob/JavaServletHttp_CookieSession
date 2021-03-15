package firstweb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fullname = request.getParameter("fullname");
		String weight = request.getParameter("weight");
		String height = request.getParameter("height");
		double bmi = Integer.parseInt(weight) / (Double.parseDouble(height) * Double.parseDouble(height));

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body><h1>สวัสดี " + fullname + "</h1>");
		out.println("<p>BMI = " + bmi + "</p>");
		out.println("</body></html>");
		System.out.println("\t\t======> " + height);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h1>นี้คือ doPost()</h1><img src='img/dog.jpg'>");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h1>นี้คือ Service()</h1><img src='img/dog.jpg'>");
	}

}
