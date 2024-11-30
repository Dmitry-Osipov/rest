package com.osipov.domain.manager.web;

import com.osipov.dto.Response;

public interface WebManager {
    Response doGet(String url);
}
