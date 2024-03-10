package org.javalabs.lab1.cache;

import org.javalabs.lab1.model.apiresponse.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ScheduleCacheTest {

    @Autowired
    private ScheduleCache scheduleCache;

    @Test
    public void testPutAndGet() {
        ApiResponse apiResponse = new ApiResponse();

        scheduleCache.put("key", apiResponse);

        ApiResponse retrievedResponse = scheduleCache.get("key");

        assertEquals(apiResponse, retrievedResponse);
    }

    @Test
    public void testPutAndGet_NullKey() {
        ApiResponse apiResponse = new ApiResponse();

        assertThrows(IllegalArgumentException.class, () -> scheduleCache.put(null, apiResponse));
    }

    @Test
    public void testGet_NonExistentKey() {
        ApiResponse retrievedResponse = scheduleCache.get("non_existent_key");

        assertNull(retrievedResponse);
    }
}
