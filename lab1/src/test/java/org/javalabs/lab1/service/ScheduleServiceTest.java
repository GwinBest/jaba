package org.javalabs.lab1.service;

import org.javalabs.lab1.dao.AuditoryRepository;
import org.javalabs.lab1.dao.LessonTypeRepository;
import org.javalabs.lab1.dao.ScheduleRepository;
import org.javalabs.lab1.entity.Schedule;
import org.javalabs.lab1.model.apiresponse.ApiResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Schedule scheduleEntity = new Schedule(); // Create a mock Schedule

        when(scheduleRepository.save(any())).thenReturn(scheduleEntity); // Mock repository method

        Schedule result = scheduleService.createSchedule(scheduleEntity); // Call the method under test

        assertEquals(scheduleEntity, result); // Check if the result is as expected
    }

    @Test
    public void testUpdateSchedule() {
        int id = 1;
        Schedule scheduleEntity = new Schedule(); // Create a mock Schedule

        when(scheduleRepository.findById(id)).thenReturn(Optional.of(scheduleEntity)); // Mock repository method
        when(scheduleRepository.save(any())).thenReturn(scheduleEntity); // Mock repository method

        Schedule result = scheduleService.updateSchedule(id, scheduleEntity); // Call the method under test

        assertEquals(scheduleEntity, result); // Check if the result is as expected
    }

    @Test
    public void testDeleteSchedule() {
        int id = 1;

        scheduleService.deleteSchedule(id); // Call the method under test

        verify(scheduleRepository, times(1)).deleteById(id); // Verify that deleteById method is called once
    }
}
