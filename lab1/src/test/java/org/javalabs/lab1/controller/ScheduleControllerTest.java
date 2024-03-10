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
import static org.mockito.Mockito.lenient;
@ExtendWith(MockitoExtension.class)
class ScheduleControllerTest {

    @Mock
    private ScheduleService scheduleService;

    @Mock
    private ScheduleCache scheduleCache;

    @InjectMocks
    private ScheduleController scheduleController;

    @Mock
    private ScheduleRepository scheduleRepository;

    @BeforeEach
    void setUp() {
        scheduleService = mock(ScheduleService.class);
        scheduleCache = mock(ScheduleCache.class);
        scheduleController = new ScheduleController(scheduleService, scheduleCache);
    }

    @Test
    public void testCreateSchedule_Success() {
        Schedule scheduleEntity = new Schedule();
        when(scheduleService.getTeacherScheduleRepository()).thenReturn(scheduleRepository);
        when(scheduleService.getTeacherScheduleRepository().findByGroupName(null)).thenReturn(null);


        ResponseEntity<String> responseEntity = scheduleController.createSchedule(scheduleEntity);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(scheduleService, times(1)).createSchedule(scheduleEntity);
    }

    @Test
    public void testCreateSchedule_Error_GroupNameExists() {
        Schedule scheduleEntity = new Schedule();
        when(scheduleService.getTeacherScheduleRepository()).thenReturn(scheduleRepository);
        when(scheduleService.getTeacherScheduleRepository().findByGroupName(null)).thenReturn(new Schedule());

        ResponseEntity<String> responseEntity = scheduleController.createSchedule(scheduleEntity);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("error", responseEntity.getBody());
        verify(scheduleService, never()).createSchedule(scheduleEntity);
    }

    @Test
    public void testUpdateSchedule_Error_EntityNotFound() {
        int id = 1;
        Schedule scheduleEntity = new Schedule();
        when(scheduleService.getTeacherScheduleRepository()).thenReturn(scheduleRepository);
        when(scheduleService.getTeacherScheduleRepository().findById(id)).thenReturn(Optional.empty());

        ResponseEntity<String> responseEntity = scheduleController.updateSchedule(id, scheduleEntity);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(scheduleService, never()).updateSchedule(anyInt(), any(Schedule.class));
    }

    @Test
    void testSearch_NotCachedResponse() {
        String query = "group1";
        ApiResponse mockResponse = new ApiResponse();
        lenient().when(scheduleCache.get(query)).thenReturn(null);
        when(scheduleService.searchPage(query)).thenReturn(mockResponse);

        ApiResponse response = scheduleController.search(query);

        assertEquals(mockResponse, response);
        verify(scheduleService, times(1)).searchPage(query);
        verify(scheduleCache, times(1)).put(query, mockResponse);
    }


    @Test
    void testDeleteSchedule_Success() {
        int id = 1;

        ResponseEntity<String> response = scheduleController.deleteSchedule(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody());
    }
}
