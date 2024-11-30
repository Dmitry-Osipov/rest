package com.osipov.domain.repository;

public class SimpleAuthRepository implements AuthRepository {

    @Override
    public String getToken() {
        return "Bearer your-bearer-token";
    }
}
