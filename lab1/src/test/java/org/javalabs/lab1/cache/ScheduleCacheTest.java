package org.javalabs.lab1.cache;

import org.javalabs.lab1.model.apiresponse.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleCacheTest {

    private ScheduleCache scheduleCache;

    @BeforeEach
    void setUp() {
        scheduleCache = new ScheduleCache();
    }

    @Test
    void testPutAndGet_Success() {
        String key = "key";
        ApiResponse value = new ApiResponse();

        scheduleCache.put(key, value);

        ApiResponse retrievedValue = scheduleCache.get(key);

        assertEquals(value, retrievedValue);
    }

    @Test
    void testGet_NonExistingKey_ReturnsNull() {
        assertNull(scheduleCache.get("nonExistingKey"));
    }
}
