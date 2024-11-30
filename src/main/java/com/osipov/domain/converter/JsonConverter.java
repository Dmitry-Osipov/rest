package com.osipov.domain.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JsonConverter implements Converter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public String fromEntity(Object object) {
        return objectMapper.writeValueAsString(object);
    }

    @Override
    @SneakyThrows
    public <T> T toEntity(String string, Class<T> clazz) {
        return objectMapper.readValue(string, clazz);
    }
}
