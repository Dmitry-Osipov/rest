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
        return converter.toEntity(getResponse("kanye-rest-quotes").getMessage(), List.class);
    }

    @Override
    public String getText() {
        return getResponse("kanye-rest-text").getMessage();
    }

    @Override
    public String get() {
        return converter.toEntity(getResponse("kanye-rest-main").getMessage(), QuoteResponse.class).getQuote();
    }

    private Response getResponse(String key) {
        String url = PropertiesReader.getProperties("application.properties").getProperty(key);
        Response response = manager.doGet(url);
        if (response.getCode() != 200) {
            throw new RuntimeException("Error: " + response.getCode());
        }
        return response;
    }
}
