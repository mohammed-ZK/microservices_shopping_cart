package com.example.api_gateway_server.filterTest;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidatorTest {

//	public static final List<String> openApiEndpoints = List.of("/auth/register", "/auth/token", "/eureka");

	public static final List<String> openApiEndpoints = List.of("/api/auth/signin", "/api/auth/signup", "/eureka");
	
	public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));

}