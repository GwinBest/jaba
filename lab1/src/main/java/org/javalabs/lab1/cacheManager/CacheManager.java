package org.javalabs.lab1.cacheManager;

import org.javalabs.lab1.model.apiResponse.ApiResponse;
import org.javalabs.lab1.logic.Logic;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CacheManager {

    @Cacheable("scheduleCache")
    public ApiResponse getCachedSchedule(String query, Logic logic) {
        return logic.searchPage(query);
    }
}
