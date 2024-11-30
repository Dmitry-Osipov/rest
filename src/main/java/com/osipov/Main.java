package com.osipov;

import com.osipov.domain.manager.auth.AuthManagerImpl;
import com.osipov.domain.manager.web.WebManagerImpl;
import com.osipov.domain.repository.SimpleAuthRepository;
import com.osipov.domain.converter.JsonConverter;
import com.osipov.domain.factory.HttpRequestFactoryImpl;
import com.osipov.domain.service.WebService;
import com.osipov.domain.service.WebServiceImpl;

public class Main {
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
    }
}
