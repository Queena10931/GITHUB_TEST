package com.iii.eeit10931.db;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/GetAllEmpsDs1")
public class GetAllEmpsDs1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;

	public GetAllEmpsDs1() {
		super();

	}

	public void init() {
		try {
			Context context=new InitialContext();
			DataSource ds =(DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			conn=ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	

	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int count = 0;

		try {
			String qryStmt = "Select*from employee";
			PreparedStatement stamt = conn.prepareStatement(qryStmt);
			ResultSet rs = stamt.executeQuery();
			out.write("<html><body bgcolor='#fdf5e6'>");
			out.write("<div align=center><h2>員工資料</h2>");
			out.write("<table border=1><tr bgcolor='#a8fefa'>");
			out.write("<th>員工編號<th>姓名<th>到職日<th>薪水<th>部門編號<th>職稱");

			while (rs.next()) {
				out.write("<tr><td>" + rs.getString("empno"));
				out.write("<td>" + rs.getString("ename"));
				out.write("<td>" + rs.getString("hiredate").substring(0,10));
				out.write("<td>" + rs.getString("salary"));
				out.write("<td>" + rs.getString("deptno"));
				out.write("<td>" + rs.getString("title"));
				count++;
			}
			out.write("</table><h3>共" + count + "筆員工資料</h3></div></body></html>");
			stamt.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
