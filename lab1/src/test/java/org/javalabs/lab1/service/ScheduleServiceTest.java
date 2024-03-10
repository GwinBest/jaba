package org.javalabs.lab1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.javalabs.lab1.dao.AuditoryRepository;
import org.javalabs.lab1.dao.LessonTypeRepository;
import org.javalabs.lab1.dao.ScheduleRepository;
import org.javalabs.lab1.entity.Auditory;
import org.javalabs.lab1.entity.LessonType;
import org.javalabs.lab1.entity.Schedule;
import org.javalabs.lab1.model.apiresponse.ApiResponse;
import org.javalabs.lab1.model.studentgroupdto.StudentGroupDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.web.client.RestTemplate;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
 class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private AuditoryRepository auditoryRepository;

    @Mock
    private LessonTypeRepository lessonTypeRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    @Test
     void testFillTables() {
        ApiResponse response = new ApiResponse();
        Schedule scheduleEntity = new Schedule();

        StudentGroupDto studentGroupDto = new StudentGroupDto();
        studentGroupDto.setName("TestGroup");
        response.setStudentGroupDto(studentGroupDto);

        org.javalabs.lab1.model.scheduledto.Schedule scheduleDto = new org.javalabs.lab1.model.scheduledto.Schedule();
        scheduleDto.setStartLessonDate("2024-03-10");
        scheduleDto.setStartLessonTime("10:00");
        scheduleDto.setLessonTypeAbbrev("Math");
        scheduleDto.setAuditories(Collections.singletonList("A101"));
        response.setSchedules(Collections.singletonMap("key", Collections.singletonList(scheduleDto)));

        LessonType existingLessonType = new LessonType();
        existingLessonType.setType("Math");
        when(lessonTypeRepository.findByType(anyString())).thenReturn(existingLessonType);
        when(auditoryRepository.save(any())).thenReturn(new Auditory());

        scheduleService.fillTables(response, scheduleEntity);

        verify(auditoryRepository, times(1)).save(any());
        verify(lessonTypeRepository, never()).save(any());
    }


    @Test
     void testCreateSchedule() {
        Schedule scheduleEntity = new Schedule();

        when(scheduleRepository.save(any())).thenReturn(scheduleEntity);

        Schedule result = scheduleService.createSchedule(scheduleEntity);

        assertEquals(scheduleEntity, result);
    }

    @Test
     void testUpdateSchedule() {
        int id = 1;
        Schedule scheduleEntity = new Schedule();

        when(scheduleRepository.findById(id)).thenReturn(Optional.of(scheduleEntity));
        when(scheduleRepository.save(any())).thenReturn(scheduleEntity);

        Schedule result = scheduleService.updateSchedule(id, scheduleEntity);

        assertEquals(scheduleEntity, result);
    }

    @Test
     void testDeleteSchedule() {
        int id = 1;

        scheduleService.deleteSchedule(id);

        verify(scheduleRepository, times(1)).deleteById(id);
    }
}
