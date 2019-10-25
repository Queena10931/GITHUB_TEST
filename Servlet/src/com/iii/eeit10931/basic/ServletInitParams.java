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

	//�Ĥ@�ؤ�k
//	public void init() {
//		servletName = getServletConfig().getServletName();
//		greeting = getServletConfig().getInitParameter("greeting");
//		count = Integer.parseInt(getServletConfig().getInitParameter("count"));
//	}
	//�ĤG�ؤ�k ������ѼƱa�J�A��config���ϥ�
	public void init(ServletConfig config) {
		servletName=config.getServletName();
		greeting=config.getInitParameter("greeting");
		count=Integer.parseInt(config.getInitParameter("count"));		
	}
//	
//	//�ĤT�ؤ�k�A�����I�s�A������ĳ�ϥΡA��ĳ�Τ@�A�G�ؤ�k
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
