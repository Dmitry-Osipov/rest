package com.osipov.domain.factory;

import com.osipov.domain.method.HttpMethod;

import java.net.HttpURLConnection;
import java.util.Map;

public interface HttpRequestFactory {
    HttpURLConnection createRequest(String url, HttpMethod method, Map<String, String> headers, String body);
}
