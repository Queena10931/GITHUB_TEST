package com.iii.eeit10931.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/UploadFiles")
@MultipartConfig
public class UploadFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadFiles() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
		
		String header = null;
		String filename = null;
//		String fileNameString = null;
		InputStream in = null;
		OutputStream out = null;

		for (Part part : request.getParts()) {

//		String filename=header.substring(header.indexOf("filename=\"") +13,
//				header.lastIndexOf("\""));
			in = part.getInputStream();
			header = part.getHeader("Content-Disposition");

			String filename_header = header.split(";")[2];

			if (filename_header.lastIndexOf("\\") != -1) {
				filename = filename_header.substring(filename_header.lastIndexOf("\\") + 1,
						filename_header.lastIndexOf("\""));
			} else {
				filename = filename_header.substring(filename_header.indexOf("filename=\"") + 10,
						filename_header.lastIndexOf("\""));
				System.out.println("filename" + filename);
			}
			if (filename.length() != 0) {
				out = new FileOutputStream("D:/uploadFile/" + filename);
				byte[] buf = new byte[1024];
				int length;
				while ((length = in.read(buf)) != -1)
					out.write(buf, 0, length);

			} else {
				PrintWriter print1;
				print1 = response.getWriter();
				print1.print("<script> alert('請上傳檔案');</script>");
				print1.close();
			}

		}

		in.close();
		out.close();


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
