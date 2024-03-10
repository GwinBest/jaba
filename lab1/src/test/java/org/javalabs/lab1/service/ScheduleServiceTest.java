package org.javalabs.lab1.service;

import org.javalabs.lab1.dao.AuditoryRepository;
import org.javalabs.lab1.dao.LessonTypeRepository;
import org.javalabs.lab1.dao.ScheduleRepository;
import org.javalabs.lab1.entity.Auditory;
import org.javalabs.lab1.entity.LessonType;
import org.javalabs.lab1.entity.Schedule;
import org.javalabs.lab1.model.apiresponse.ApiResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private AuditoryRepository auditoryRepository;

    @Mock
    private LessonTypeRepository lessonTypeRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    @Test
    public void testCreateSchedule() {
        Schedule scheduleEntity = new Schedule();
        when(scheduleRepository.save(scheduleEntity)).thenReturn(scheduleEntity);

        Schedule createdSchedule = scheduleService.createSchedule(scheduleEntity);

        assertNotNull(createdSchedule);
        assertEquals(scheduleEntity, createdSchedule);
    }

    @Test
    public void testUpdateSchedule() {
        int id = 1;
        Schedule scheduleEntity = new Schedule();
        when(scheduleRepository.findById(id)).thenReturn(Optional.of(scheduleEntity));
        when(scheduleRepository.save(scheduleEntity)).thenReturn(scheduleEntity);

        Schedule updatedSchedule = scheduleService.updateSchedule(id, scheduleEntity);

        assertNotNull(updatedSchedule);
        assertEquals(scheduleEntity, updatedSchedule);
    }

    @Test
    public void testUpdateSchedule_NonExistingId() {
        int id = 1;
        Schedule scheduleEntity = new Schedule();
        when(scheduleRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> scheduleService.updateSchedule(id, scheduleEntity));
    }

    @Test
    public void testDeleteSchedule() {
        int id = 1;
        doNothing().when(scheduleRepository).deleteById(id);

        assertDoesNotThrow(() -> scheduleService.deleteSchedule(id));
    }

    @Test
    public void testSearchPage() {
        String query = "group1";
        String apiUrl = "https://iis.bsuir.by/api/v1/schedule?studentGroup=" + query;
        ApiResponse apiResponse = new ApiResponse();

        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.getForObject(apiUrl, ApiResponse.class)).thenReturn(apiResponse);

        when(scheduleRepository.findByGroupName(query)).thenReturn(null);

        ApiResponse response = scheduleService.searchPage(query);

        assertNotNull(response);
    }
}
