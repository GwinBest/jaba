package org.javalabs.lab1.service;

import org.javalabs.lab1.dao.AuditoryRepository;
import org.javalabs.lab1.dao.ScheduleRepository;
import org.javalabs.lab1.entity.Auditory;
import org.javalabs.lab1.entity.Schedule;
import org.javalabs.lab1.model.auditorydto.AuditoryDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
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
    public void testCreateAuditoriesBulk_ValidData() throws Exception {
        List<Auditory> auditories = new ArrayList<>();
        String group = "group1";
        when(scheduleRepository.findByGroupName(group)).thenReturn(new Schedule());
        when(auditoryRepository.save(any(Auditory.class))).thenReturn(new Auditory());

        auditoryService.createAuditoriesBulk(auditories, group);
    }

    @Test
    public void testGetAuditoriesByDateAndScheduleId_ValidData() {
        String date = "2024-03-10";
        String groupName = "group1";
        List<AuditoryDto> expectedAuditoryDtoList = new ArrayList<>();
        when(scheduleRepository.findByGroupName(groupName)).thenReturn(new Schedule());
        when(auditoryRepository.findByDateAndScheduleId(date, 1)).thenReturn(expectedAuditoryDtoList);

        List<AuditoryDto> actualAuditoryDtoList = auditoryService.getAuditoriesByDateAndScheduleId(date, groupName);

        assertEquals(expectedAuditoryDtoList, actualAuditoryDtoList);
    }


    @Test
    public void testUpdateAuditory_ValidData() {
        int id = 1;
        Auditory existingAuditory = new Auditory();
        Auditory updatedAuditory = new Auditory();
        updatedAuditory.setId(id);
        when(auditoryRepository.findById(id)).thenReturn(Optional.of(existingAuditory));
        when(auditoryRepository.save(updatedAuditory)).thenReturn(updatedAuditory);

        Auditory returnedAuditory = auditoryService.updateAuditory(id, updatedAuditory);

        assertEquals(updatedAuditory, returnedAuditory);
    }

    @Test
    public void testDeleteAuditory_ValidId() {
        int id = 1;
        doNothing().when(auditoryRepository).deleteById(id);

        auditoryService.deleteAuditory(id);
    }

}
