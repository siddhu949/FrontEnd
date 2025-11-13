package com.example.spring.boot.filters.filter;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ProductFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("[messageFilter]-inside Dofilter method");
		log.info("local port:"+request.getLocalPort());
		log.info("server name"+request.getServerName());
		
		HttpServletRequest httpServletRequest=(HttpServletRequest) request;
		log.info("method name:"+httpServletRequest.getMethod());
		log.info("Request uri:"+httpServletRequest.getRequestURI());
		log.info("method name:"+httpServletRequest.getServletPath());
		filterChain.doFilter(request, response);
		
	}
	
}
