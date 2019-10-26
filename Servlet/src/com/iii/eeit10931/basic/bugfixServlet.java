package com.iii.eeit10931.basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/bugfixServlet")
public class bugfixServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public bugfixServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("XXXXXbugfixServlet Served at: ")
		.append(request.getContextPath()).append("模擬另一個環境重新上傳bugfixServlet Served at: ")
		.append("測試第三次");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
