package com.osipov.domain.service;

import com.osipov.domain.auth.manager.web.WebManager;
import com.osipov.domain.converter.Converter;
import com.osipov.dto.QuoteResponse;
import com.osipov.dto.Response;
import com.osipov.environment.PropertiesReader;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class WebServiceImpl implements WebService {
    private final WebManager manager;
    private final Converter converter;
    
    @Override
    public List<String> getQuotes() {
        String url = PropertiesReader.getProperties("application.properties").getProperty("kanye-rest-quotes");
        Response response = manager.doGet(url);
        if (response.getCode() != 200) {
            throw new RuntimeException("Error: " + response.getCode());
        }

        return converter.toEntity(response.getMessage(), List.class);
    }

    @Override
    public String getText() {
        String url = PropertiesReader.getProperties("application.properties").getProperty("kanye-rest-text");
        Response response = manager.doGet(url);
        if (response.getCode() != 200) {
            throw new RuntimeException("Error: " + response.getCode());
        }

        return response.getMessage();
    }

    @Override
    public String get() {
        String url = PropertiesReader.getProperties("application.properties").getProperty("kanye-rest-main");
        Response response = manager.doGet(url);
        if (response.getCode() != 200) {
            throw new RuntimeException("Error: " + response.getCode());
        }

        return converter.toEntity(response.getMessage(), QuoteResponse.class).getQuote();
    }
}
