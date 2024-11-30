package com.osipov.domain.manager.auth;

import com.osipov.domain.repository.AuthRepository;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class AuthManagerImpl implements AuthManager {
    private final AuthRepository repository;

    @Override
    public Map<String, String> getAuthHeaders() {
        return Map.of("Authorization", repository.getToken());
    }
}
