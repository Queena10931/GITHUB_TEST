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

@WebServlet("/UpdateInsertDB")
public class UpdateInsertDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;

	public UpdateInsertDB() {
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
request.setCharacterEncoding("UTF-8");
		
		String empno=request.getParameter("empno");
		String ename=request.getParameter("ename");
		String hiredate=request.getParameter("hiredate");
		String salary=request.getParameter("salary");
		String deptno=request.getParameter("deptno");
		String title=request.getParameter("title");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int count=0;
		
		try {		
			String sqlstr="update employee set ename=?, hiredate=?, salary=?, deptno=?, title=? where empno=?";
			PreparedStatement state = conn.prepareStatement(sqlstr);
			state.setString(1,ename);
			state.setString(2,hiredate);
			state.setString(3,salary);
			state.setString(4,deptno);
			state.setString(5,title);
			state.setString(6,empno);
			
			state.executeUpdate();
			state.close();
			
			//�s�W��d��
			String qryStmt = "Select*from employee";
			PreparedStatement state1 = conn.prepareStatement(qryStmt);
			ResultSet rs = state1.executeQuery();
			out.write("<html><body bgcolor='#fdf5e6'>");
			out.write("<div align=center><h2>���u���</h2>");
			out.write("<table border=1><tr bgcolor='#a8fefa'>");
			out.write("<th>���u�s��<th>�m�W<th>��¾��<th>�~��<th>�����s��<th>¾��");

			while (rs.next()) {
				out.write("<tr><td>" + rs.getString("empno"));
				out.write("<td>" + rs.getString("ename"));
				out.write("<td>" + rs.getString("hiredate").substring(0,10));
				out.write("<td>" + rs.getString("salary"));
				out.write("<td>" + rs.getString("deptno"));
				out.write("<td>" + rs.getString("title"));
				count++;
			}
			out.write("</table><h3>�@" + count + "�����u���</h3></div></body></html>");
			state.close();
			out.print("�w�ק���u�s��:"+empno+" �m�W:"+ename);
			
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
