package com.iii.eeit10931.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GetCookieInfo")
public class GetCookieInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public GetCookieInfo() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out =response.getWriter();
		out.write("<html><body><h2>Cookie¸ê°T</h2>");
		
		Cookie[] cookie = request.getCookies();
		if(cookie!=null) {
			for(Cookie c:cookie) {
				out.write(c.getName()+"="+c.getValue()+"<br>");
			}
		
		}
		else {
			out.write("The request has no cookie!!");
		}
		out.write("</body></html>");
		
		String name=request.getParameter("cookieName");
		
		if(name!=null && name.length()>0) {
			String value= request.getParameter("cookieValue");
			Cookie c= new Cookie(name, value);
			response.addCookie(c);
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
