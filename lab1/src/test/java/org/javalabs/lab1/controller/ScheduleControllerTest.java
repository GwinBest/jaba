package org.javalabs.lab1.controller;

import org.javalabs.lab1.cache.ScheduleCache;
import org.javalabs.lab1.dao.ScheduleRepository;
import org.javalabs.lab1.entity.Schedule;
import org.javalabs.lab1.model.apiresponse.ApiResponse;
import org.javalabs.lab1.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleControllerTest {

    @Mock
    private ScheduleService scheduleService;

    @Mock
    private ScheduleCache scheduleCache;

    @InjectMocks
    private ScheduleController scheduleController;

    @BeforeEach
    void setUp() {
        scheduleService = mock(ScheduleService.class);
        scheduleCache = mock(ScheduleCache.class);
        scheduleController = new ScheduleController(scheduleService, scheduleCache);
    }

    @Test
    void testSearch_CachedResponse() {
        String query = "group1";
        ApiResponse cachedResponse = new ApiResponse();
        when(scheduleCache.get(query)).thenReturn(cachedResponse);

        ApiResponse response = scheduleController.search(query);

        assertEquals(cachedResponse, response);
        verify(scheduleService, never()).searchPage(query);
        verify(scheduleCache, times(1)).get(query);
    }

    @Test
    void testSearch_NotCachedResponse() {
        String query = "group1";
        ApiResponse mockResponse = new ApiResponse();
        when(scheduleCache.get(query)).thenReturn(null);
        when(scheduleService.searchPage(query)).thenReturn(mockResponse);

        ApiResponse response = scheduleController.search(query);

        assertEquals(mockResponse, response);
        verify(scheduleService, times(1)).searchPage(query);
        verify(scheduleCache, times(1)).put(query, mockResponse);
    }

    @Test
    void testDeleteSchedule_Success() {
        int id = 1;
        doNothing().when(scheduleService).deleteSchedule(id);

        ResponseEntity<String> response = scheduleController.deleteSchedule(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody());
    }



}
