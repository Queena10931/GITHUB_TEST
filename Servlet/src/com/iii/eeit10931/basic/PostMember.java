package com.iii.eeit10931.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PostMember")
public class PostMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public PostMember() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.append("<html><head><title>Post Parameters Demo</title></head>")
		.append("<body bgcolor='#fdf5e6'><div align=center>")
		.append("<h2>會員資料</h2><table border=1>")
		.append("<tr bgcolor='#ffad00'><th>名稱<th>值");
		
		Enumeration<String> paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String paramName= paramNames.nextElement();
			out.append("<tr><td>"+paramName+"<td>");
			String[] paramValues = request.getParameterValues(paramName);
			if(paramValues.length==1) {
				String paramValue = paramValues[0];
				out.append((paramValue.length()==0)?"<I>No Value</I>":paramValue);
			}
			else {
				for(int i =0;i<paramValues.length;i++) {
					out.append(paramValues[i]);
				}
			}
			
		}
		out.append("</table></div></body></html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
