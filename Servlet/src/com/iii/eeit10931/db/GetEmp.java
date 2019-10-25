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

@WebServlet("/GetEmp")
public class GetEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;

	public GetEmp() {
		super();

	}

	public void init() {
    	try {
    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=servdb";
		conn = DriverManager.getConnection(connUrl, "sa", "P@ssw0rd");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


    	

	}

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String empno=request.getParameter("empno");
		response.setContentType("text/html;charset=UTF-8");
		

		PrintWriter out = response.getWriter();

		try {
			String qryStmt = "Select *from employee where empno=?";
			PreparedStatement stmt = conn.prepareStatement(qryStmt);
			stmt.setString(1, empno);
			ResultSet rs = stmt.executeQuery();
			out.write("<html><body bgcolor=\'#fdf5e6\'>");
			out.write("<div align=center><h2>員工資料</h2>");
			
			if (rs.next()) {
				out.write("<table width=60%>");
				out.write("<tr><td width=30%>員工編號<td>" + rs.getString("empno"));
				out.write("<tr><td>姓名<td>"+ rs.getString("ename"));
				out.write("<tr><td>到職日<td>" + rs.getString("hiredate").substring(0,10));
				out.write("<tr><td>薪水<td>" + rs.getString("salary"));
				out.write("<tr><td>部門編號<td>" + rs.getString("deptno"));
				out.write("<tr><td>職稱<td>" + rs.getString("title"));
				out.write("</table></div>");
				
			}
			else {
				
				out.write("請勿空白");
			}
			out.write("</body></html>");
			stmt.close();

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
