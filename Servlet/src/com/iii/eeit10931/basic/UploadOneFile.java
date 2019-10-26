package com.iii.eeit10931.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/UploadOneFile")
@MultipartConfig
public class UploadOneFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadOneFile() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Part part=request.getPart("photo");
		InputStream in= part.getInputStream();
		String header=part.getHeader("Content-Disposition");
		
		System.out.println(header);
		System.out.println(header);
		
		
//		String filename=header.substring(header.indexOf("filename=\"") +13,
//				header.lastIndexOf("\""));
		
		
		String filename_header=header.split(";")[2];
		//會分割成這樣三部分，取陣列第二個檔名來做判斷
		//form-data; name="photo"; filename="檔名.gif"
		String filename;
		
		if(filename_header.lastIndexOf("\\") !=-1) {
			filename=filename_header.substring(filename_header.lastIndexOf("\\")+1,
					filename_header.lastIndexOf("\""));
		//會先從其他檔案中找尋是否有符合，有符合就會進入if，沒符合就會進入到Else
		}
		else {
			filename=filename_header.substring(filename_header.lastIndexOf("filename=\"") +10,
					filename_header.lastIndexOf("\""));
			System.out.println("filename"+filename);
			
		}
		
		
		
		OutputStream out= new FileOutputStream("D:/uploadFile/" + filename);
		byte[] buf =new byte[1024];
		int length;
		while ((length=in.read(buf)) !=-1) {
			out.write(buf,0,length);
			
		}
		in.close();
		out.close();
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
