package com.iii.eeit10931.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GetClientInfo")
public class GetClientInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public GetClientInfo() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		  response.setContentType("text/html;charset=UTF-8");
		  PrintWriter out = response.getWriter();
		  out.write("<html><head><title>Client Information Demo</title></head>");
		  out.write("<body><h2>ClientºÝªº¸ê°T</h2>");
		  out.write("Scheme : "+request.getScheme()+"<br>");
		  out.write("ServerName : "+request.getServerName()+"<br>");
		  out.write("ServerPort : "+request.getServerPort()+"<br>");
		  out.write("ContextPath : "+request.getContextPath()+"<br>");
		  out.write("ServletPath : "+request.getServletPath()+"<br>");
		  out.write("Method : "+request.getMethod()+"<br>");
		  out.write("Protocol : "+request.getProtocol()+"<br>");
		  out.write("URI : "+request.getRequestURI()+"<br>");
		  out.write("RemoteHost : "+request.getRemoteHost()+"<br>");
		  out.write("RemoteAdder : "+request.getContentType()+"<br>");
		  out.write("ContentType : "+request.getContentType()+"<br>");
		  out.write("ContentLength : "+request.getContentLength()+"<p>");
		  
		  Enumeration<String> names = request.getHeaderNames();
		  while(names.hasMoreElements()) {
		   String name = names.nextElement();
		   String value = request.getHeader(name);
		   out.write("headername = "+ name + ", headervalue = "+ value + "<br>");
		  }//end of while
		  out.write("</body></html>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
