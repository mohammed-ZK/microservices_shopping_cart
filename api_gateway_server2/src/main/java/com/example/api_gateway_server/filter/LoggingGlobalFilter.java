package com.example.api_gateway_server.filter;


import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ReactiveHttpInputMessage;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

//@Component
public class LoggingGlobalFilter extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // Capture request body
            String requestBody = resolveBody(request);

            // Continue the filter chain
            return chain.filter(exchange.mutate().request(request).build())
                    .then(Mono.fromRunnable(() -> {
                        // Capture response body
                        response.beforeCommit(() -> {
                            String responseBody = resolveBody(response);
                            // Log or process the request and response bodies as needed
                            System.out.println("Request Body: " + requestBody);
                            System.out.println("Response Body: " + responseBody);
                            return Mono.empty();
                        });
                    }));
        };
    }

    private String resolveBody(ServerHttpRequest request) {
        // Resolve the request body as a string
        // Note: You may need to customize this based on your requirements
        return request.getBody()
                .map(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    // Assuming UTF-8 encoding, modify if needed
                    return new String(bytes, java.nio.charset.StandardCharsets.UTF_8);
                })
                .blockLast();
    }

    private String resolveBody(ServerHttpResponse response) {
        // Resolve the response body as a string
        // Note: You may need to customize this based on your requirements
        return ((ReactiveHttpInputMessage) response).getBody()
                .map(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    // Assuming UTF-8 encoding, modify if needed
                    return new String(bytes, java.nio.charset.StandardCharsets.UTF_8);
                })
                .blockLast();
    }
}
