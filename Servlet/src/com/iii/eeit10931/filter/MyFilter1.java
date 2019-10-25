package com.iii.eeit10931.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter("/FilterDemo")
public class MyFilter1 implements Filter {

  
    public MyFilter1() {
        
    }
    //生命週期的用法
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		PrintWriter out= response.getWriter();
		//前置處理位置
		out.write("Pre-processing of Myfilter1<br>");
		chain.doFilter(request, response);
		//後製處理位置
		out.write("Post-processing of Myfilter1<br>");
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
