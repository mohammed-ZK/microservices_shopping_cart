package com.example.api_gateway_server.cofiguration;

import java.nio.charset.StandardCharsets;

import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.ReactiveHttpInputMessage;
import org.springframework.http.server.reactive.ServerHttpRequest;

import reactor.core.publisher.Mono;

public class RequestBodyReader__t {

    public static Mono<String> readRequestBody(ServerHttpRequest request) {
        return DataBufferUtils.join(((ReactiveHttpInputMessage) request).getBody())
                .map(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);
                    return new String(bytes, StandardCharsets.UTF_8);
                });
    }
}

