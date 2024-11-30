package com.osipov.domain.manager.web;

import com.osipov.dto.Response;
import com.osipov.domain.manager.auth.AuthManager;
import com.osipov.domain.factory.HttpRequestFactory;
import com.osipov.domain.method.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

@AllArgsConstructor
public class WebManagerImpl implements WebManager {
    private final AuthManager authManager;
    private final HttpRequestFactory requestFactory;

    @Override
    @SneakyThrows
    public Response doGet(String url) {
        HttpURLConnection connection =
                requestFactory.createRequest(url, HttpMethod.GET, authManager.getAuthHeaders(), null);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return new Response(connection.getResponseCode(), response.toString());
        }
    }
}
