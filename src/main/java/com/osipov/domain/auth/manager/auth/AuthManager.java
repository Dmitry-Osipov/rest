package com.osipov.domain.auth.manager.auth;

import java.util.Map;

public interface AuthManager {
    Map<String, String> getAuthHeaders();
}
