package com.example.api_gateway_server.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

////@Configuration
//public class LoggingFilterConfig {
//
//    @Bean
//    @Order(-1)
//    public GlobalFilter globalFilter() {
//        return (exchange, chain) -> {
//            // Log the request details
//            System.out.println("Request URI: " + exchange.getRequest().getURI());
//            System.out.println("Request Method: " + exchange.getRequest().getMethod());
//
//            // Continue the filter chain
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                // Log the response details
//                System.out.println("Response Status Code: " + exchange.getResponse().getStatusCode());
//            }));
//        };
//    }
//}
//
//@Configuration
//public class LoggingFilter extends OncePerRequestFilter {
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		LOGGER.debug("ssssssssssssssssssssssssssssss");
//		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
//		ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
//
//		LOGGER.debug("ssssssssssssssssssssssssssssss1");
//		long startTime = System.currentTimeMillis();
//		filterChain.doFilter(requestWrapper, responseWrapper);
//		long timeTaken = System.currentTimeMillis() - startTime;
//
//		LOGGER.debug("ssssssssssssssssssssssssssssss2");
//		String requestBody = getStringValue(requestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
//		String responseBody = getStringValue(responseWrapper.getContentAsByteArray(), response.getCharacterEncoding());
//
//		LOGGER.info(
//				"FINISHED PROCESSING : METHOD={}; REQUESTURI={}; REQUEST PAYLOAD={}; RESPONSE CODE={}; RESPONSE={}; TIM TAKEN={}",
//				request.getMethod(), request.getRequestURI(), requestBody, response.getStatus(), responseBody,
//				timeTaken);
//		responseWrapper.copyBodyToResponse();
//
//	}
//	private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
//		try {
//			return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return "";
//	}

//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
//		ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
//
//		long startTime = System.currentTimeMillis();
//		filterChain.doFilter(requestWrapper, responseWrapper);
//		long timeTaken = System.currentTimeMillis() - startTime;
//
//		String requestBody = getStringValue(requestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
//		String responseBody = getStringValue(responseWrapper.getContentAsByteArray(), response.getCharacterEncoding());
//
//		LOGGER.info(
//				"FINISHED PROCESSING : METHOD={}; REQUESTURI={}; REQUEST PAYLOAD={}; RESPONSE CODE={}; RESPONSE={}; TIM TAKEN={}",
//				request.getMethod(), request.getRequestURI(), requestBody, response.getStatus(), responseBody,
//				timeTaken);
//		responseWrapper.copyBodyToResponse();
//	}
//
//	private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
//		try {
//			return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return "";
//	}
//
//	@Override
//	protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
//			jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
//			throws jakarta.servlet.ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//	}

//}
