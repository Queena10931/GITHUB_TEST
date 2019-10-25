package com.iii.eeit10931.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

//URL�W�٭n�s��servlet
//@WebFilter(urlPatterns = "/*",  �L�k��*�A�|�y����L�������
//initParams = @WebInitParam(name = "encoding",value="UTF-8"))
@WebFilter(urlPatterns = {"/EncodingFilterDemo", "/EncodingFilterDemo2"}, 
initParams = @WebInitParam(name = "encoding", value = "UTF-8"))

public class EncodingFilter implements Filter {

	public EncodingFilter() {

	}

	String encoding;

	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
	}

	// �ͩR�g�����Ϊk
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// filter�e�m�B�z
		if (encoding != null) {
			request.setCharacterEncoding(encoding);
			response.setContentType("text/html;charset=" + encoding);
		}
		chain.doFilter(request, response);
		// ��s�B�z��m

	}

}
