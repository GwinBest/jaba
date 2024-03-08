package org.javalabs.lab1.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.javalabs.lab1.model.apiresponse.ApiResponse;
import org.springframework.stereotype.Component;

@Component
public class ScheduleCache {
    private final Map<String, ApiResponse> cache = new ConcurrentHashMap<>();

    public void put(String key, ApiResponse value) {
        cache.put(key, value);
    }

    public ApiResponse get(String key) {
        return cache.get(key);
    }
}

