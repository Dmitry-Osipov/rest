package com.osipov.domain.factory;

import com.osipov.domain.method.HttpMethod;
import lombok.SneakyThrows;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpRequestFactoryImpl implements HttpRequestFactory {

    @Override
    @SneakyThrows
    public HttpURLConnection createRequest(String path, HttpMethod httpMethod,
                                           Map<String, String> headers, String body) {
        URL url = new URI(path).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String method = httpMethod.getName();
        connection.setRequestMethod(method);

        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        if (body != null
                && !body.isBlank()
                && ("POST".equals(method) || "PUT".equals(method))) {
            connection.setDoOutput(true);

            try (OutputStream out = connection.getOutputStream()) {
                out.write(body.getBytes(StandardCharsets.UTF_8));
            }
        }

        return connection;
    }
}
