package org.javalabs.lab1.service;

import jakarta.persistence.EntityNotFoundException;
import org.javalabs.lab1.dao.AuditoryRepository;
import org.javalabs.lab1.dao.ScheduleRepository;
import org.javalabs.lab1.entity.Auditory;
import org.javalabs.lab1.entity.Schedule;
import org.javalabs.lab1.model.auditorydto.AuditoryDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuditoryServiceTest {

    @Mock
    private AuditoryRepository auditoryRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private AuditoryService auditoryService;

    @Test
    public void testGetAuditoryById_ExistingId() {
        int id = 1;
        Auditory expectedAuditory = new Auditory();
        when(auditoryRepository.findById(id)).thenReturn(Optional.of(expectedAuditory));

        Auditory actualAuditory = auditoryService.getAuditoryById(id);

        assertEquals(expectedAuditory, actualAuditory);
    }

    @Test
    public void testGetAuditoryById_NonExistingId() {
        int id = 1;
        when(auditoryRepository.findById(id)).thenReturn(Optional.empty());

        Auditory actualAuditory = auditoryService.getAuditoryById(id);

        assertNull(actualAuditory);
    }

    @Test
    public void testCreateAuditory_ValidData() {
        Auditory auditoryEntity = new Auditory();
        String group = "group1";
        Schedule schedule = new Schedule();
        when(scheduleRepository.findByGroupName(group)).thenReturn(schedule);
        when(auditoryRepository.save(auditoryEntity)).thenReturn(auditoryEntity);

        Auditory createdAuditory = auditoryService.createAuditory(auditoryEntity, group);

        assertNotNull(createdAuditory);
        assertEquals(schedule, createdAuditory.getSchedule());
    }

    @Test
    public void testCreateAuditory_NullEntity() {
        assertThrows(IllegalArgumentException.class, () -> auditoryService.createAuditory(null, "group1"));
    }

    @Test
    public void testCreateAuditory_NoSuchGroup() {
        Auditory auditoryEntity = new Auditory();
        String group = "nonexistentGroup";
        when(scheduleRepository.findByGroupName(group)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> auditoryService.createAuditory(auditoryEntity, group));
    }

    @Test
    void testDeleteAuditory_ValidId() {
        int id = 1;
        when(auditoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> auditoryService.deleteAuditory(id));

        verify(auditoryRepository, never()).deleteById(id);
    }

    @Test
    void testDeleteAuditory_InvalidId() {
        int id = 1;
        Auditory auditory = new Auditory();
        when(auditoryRepository.findById(id)).thenReturn(Optional.of(auditory));

        auditoryService.deleteAuditory(id);

        verify(auditoryRepository, times(1)).deleteById(id);
    }

}
