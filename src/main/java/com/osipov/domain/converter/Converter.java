package com.osipov.domain.converter;

public interface Converter {
    String fromEntity(Object object);
    <T> T toEntity(String string, Class<T> clazz);
}
