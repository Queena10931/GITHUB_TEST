package com.iii.eeit10931.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/GetSessionInfo")
@MultipartConfig
public class GetSessionInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetSessionInfo() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);
		String attName = request.getParameter("attName");
		if (attName != null && attName.length() > 0) {
			String attValue = request.getParameter("attValue");
			session.setAttribute(attName, attValue);
		}

		PrintWriter out = response.getWriter();
		Date created = new Date(session.getCreationTime());
		Date accessed = new Date(session.getLastAccessedTime());
		out.write("<html><body><h2>Session¸ê°T</h2>");
		out.write("ID:" + session.getId() + "<br>");
		out.write("Created:" + created + "<br>");
		out.write("Last Accessed:" + accessed + "<p>");

		Enumeration<String> names = session.getAttributeNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = session.getAttribute(name).toString();
			out.write(name + "=" + value + "<br>");
		}
		out.write("</body></html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
