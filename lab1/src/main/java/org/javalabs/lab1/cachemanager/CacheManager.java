package org.javalabs.lab1.cachemanager;

import org.javalabs.lab1.model.apiresponse.ApiResponse;
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
