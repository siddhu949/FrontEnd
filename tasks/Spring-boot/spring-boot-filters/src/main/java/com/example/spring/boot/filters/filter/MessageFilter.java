package com.example.spring.boot.filters.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("[messageFilter]-inside Dofilter method");
		log.info("local port:"+request.getLocalPort());
		log.info("server name"+request.getServerName());
		
		HttpServletRequest httpServletRequest=(HttpServletRequest) request;
		log.info("method name:"+httpServletRequest.getMethod());
		log.info("Request uri:"+httpServletRequest.getRequestURI());
		log.info("method name:"+httpServletRequest.getServletPath());
		chain.doFilter(request, response);
		
		
		
	}

}
