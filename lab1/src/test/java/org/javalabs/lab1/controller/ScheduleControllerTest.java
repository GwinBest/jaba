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

/*    @Test
    void testCreateSchedule_Success() {
        Schedule scheduleEntity = new Schedule();
        when(scheduleService.createSchedule(scheduleEntity)).thenReturn(scheduleEntity);
        when(scheduleService.getTeacherScheduleRepository()).thenReturn(mock(ScheduleRepository.class)); // Мокируем ScheduleRepository

        ResponseEntity<String> response = scheduleController.createSchedule(scheduleEntity);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("success", response.getBody());
    }*/

/*    @Test
    void testUpdateSchedule_Success() {
        int id = 1;
        Schedule scheduleEntity = new Schedule();
        scheduleEntity.setGroupName("Group1");
        when(scheduleService.updateSchedule(id, scheduleEntity)).thenReturn(scheduleEntity);

        ResponseEntity<String> response = scheduleController.updateSchedule(id, scheduleEntity);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody());
    }*/

    @Test
    void testDeleteSchedule_Success() {
        int id = 1;
        doNothing().when(scheduleService).deleteSchedule(id);

        ResponseEntity<String> response = scheduleController.deleteSchedule(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody());
    }

}
