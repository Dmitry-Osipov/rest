package com.osipov.domain.manager.auth;

import java.util.Map;

public interface AuthManager {
    Map<String, String> getAuthHeaders();
}
