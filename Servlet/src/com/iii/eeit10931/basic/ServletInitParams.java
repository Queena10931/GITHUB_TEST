package com.iii.eeit10931.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/ServletInitParams",
initParams = {@WebInitParam(name = "greeting",value ="Have a nice day!!"),
@WebInitParam(name = "count",value = "3")})

public class ServletInitParams extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServletInitParams() {
        super();
       
    }
    String servletName;
	String greeting;
	int count;

	//第一種方法
//	public void init() {
//		servletName = getServletConfig().getServletName();
//		greeting = getServletConfig().getInitParameter("greeting");
//		count = Integer.parseInt(getServletConfig().getInitParameter("count"));
//	}
	//第二種方法 直接把參數帶入，用config取使用
	public void init(ServletConfig config) {
		servletName=config.getServletName();
		greeting=config.getInitParameter("greeting");
		count=Integer.parseInt(config.getInitParameter("count"));		
	}
//	
//	//第三種方法，直接呼叫，但不建議使用，建議用一，二種方法
//	public void init() {
//		servletName=getServletName();
//		greeting=getInitParameter("greeting");
//		count=Integer.parseInt(getInitParameter("count"));
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out= response.getWriter();
		out.write("<html><body><h3>HelloWorld "+servletName+"<h3>");
		for (int i = 0; i < count; i++) {
			out.write(greeting+"<br>");
		}
		out.write("</body></html>");	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
