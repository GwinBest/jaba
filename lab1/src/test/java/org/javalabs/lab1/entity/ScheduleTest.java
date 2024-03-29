package org.javalabs.lab1.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.ArrayList;
import  java.util.List;

@ExtendWith(MockitoExtension.class)
 class ScheduleTest {

    @Test
    void testSetAuditoryEntities() {
        Schedule schedule = new Schedule();

        List<Auditory> auditoryEntities = new ArrayList<>();
        auditoryEntities.add(new Auditory());
        auditoryEntities.add(new Auditory());
        auditoryEntities.add(new Auditory());

        schedule.setAuditoryEntities(auditoryEntities);

        assertEquals(auditoryEntities, schedule.getAuditoryEntities());
    }

    @Test
     void testGettersAndSetters() {
        Schedule schedule = new Schedule();
        schedule.setId(1);
        schedule.setGroupName("Group1");
        schedule.setFacultyId(123);
        schedule.setFacultyAbbrev("ABC");
        schedule.setCourse(2);

        assertEquals(1, schedule.getId());
        assertEquals("Group1", schedule.getGroupName());
        assertEquals(123, schedule.getFacultyId());
        assertEquals("ABC", schedule.getFacultyAbbrev());
        assertEquals(2, schedule.getCourse());
    }

    @Test
     void testEmptyConstructor() {
        Schedule schedule = new Schedule();
        assertEquals(0, schedule.getId());
        assertNull(schedule.getGroupName());
        assertEquals(0, schedule.getFacultyId());
        assertNull(schedule.getFacultyAbbrev());
        assertEquals(0, schedule.getCourse());
        assertNull(schedule.getAuditoryEntities());
    }
}

