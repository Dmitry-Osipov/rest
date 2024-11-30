package com.osipov;

import com.osipov.domain.auth.manager.auth.AuthManagerImpl;
import com.osipov.domain.auth.manager.web.WebManagerImpl;
import com.osipov.domain.auth.repository.SimpleAuthRepository;
import com.osipov.domain.service.WebService;
import com.osipov.domain.service.WebServiceImpl;
import com.osipov.dto.Response;
import com.osipov.environment.PropertiesReader;
import com.osipov.domain.converter.Converter;
import com.osipov.domain.converter.JsonConverter;
import com.osipov.domain.factory.HttpRequestFactory;
import com.osipov.domain.factory.HttpRequestFactoryImpl;
import com.osipov.domain.method.HttpMethod;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Properties;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        WebService service = new WebServiceImpl(
                new WebManagerImpl(
                        new AuthManagerImpl(
                                new SimpleAuthRepository()
                        ),
                        new HttpRequestFactoryImpl()
                ),
                new JsonConverter()
        );
        System.out.println(service.get() + "\n");
        System.out.println(service.getText() + "\n");
        service.getQuotes().forEach(System.out::println);
//        Properties prop = PropertiesReader.getProperties("application.properties");
//        HttpRequestFactory requestFactory = new HttpRequestFactoryImpl();
//        HttpURLConnection connection = requestFactory.createRequest(
//                prop.getProperty("kanye-rest-text"),
//                HttpMethod.GET,
//                null,
//                null
//        );
//
//        System.out.println(connection.getResponseCode());
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
//            StringBuilder response = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                response.append(line);
//            }
//            System.out.println(response);
//        }
    }
}
