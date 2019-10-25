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

//URL名稱要連接servlet
//@WebFilter(urlPatterns = "/*",  無法用*，會造成其他的不能用
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

	// 生命週期的用法
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// filter前置處理
		if (encoding != null) {
			request.setCharacterEncoding(encoding);
			response.setContentType("text/html;charset=" + encoding);
		}
		chain.doFilter(request, response);
		// 後製處理位置

	}

}
