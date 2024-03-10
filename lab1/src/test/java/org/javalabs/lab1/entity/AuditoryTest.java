package org.javalabs.lab1.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class AuditoryTest {

    @Test
    public void testGettersAndSetters() {
        Auditory auditory = new Auditory();
        auditory.setId(1);
        auditory.setAuditoryNumber("A101");
        auditory.setGroupName("Group1");
        auditory.setStartTime("08:00");
        auditory.setDate("2024-03-10");

        assertEquals(1, auditory.getId());
        assertEquals("A101", auditory.getAuditoryNumber());
        assertEquals("Group1", auditory.getGroupName());
        assertEquals("08:00", auditory.getStartTime());
        assertEquals("2024-03-10", auditory.getDate());
    }

    @Test
    public void testEmptyConstructor() {
        Auditory auditory = new Auditory();
        assertEquals(0, auditory.getId());
        assertNull(auditory.getAuditoryNumber());
        assertNull(auditory.getGroupName());
        assertNull(auditory.getStartTime());
        assertNull(auditory.getDate());
    }

    @Test
    public void testLessonTypes() {
        Auditory auditory = new Auditory();
        LessonType lessonType1 = new LessonType();
        LessonType lessonType2 = new LessonType();
        auditory.getLessonTypes().add(lessonType1);
        auditory.getLessonTypes().add(lessonType2);

        assertEquals(2, auditory.getLessonTypes().size());
        assertEquals(lessonType1, auditory.getLessonTypes().get(0));
        assertEquals(lessonType2, auditory.getLessonTypes().get(1));

        auditory.setLessonTypes(new ArrayList<>());
        assertEquals(0, auditory.getLessonTypes().size());
    }

    @Test
    public void testSchedule() {
        Auditory auditory = new Auditory();
        Schedule schedule = new Schedule();
        auditory.setSchedule(schedule);

        assertEquals(schedule, auditory.getSchedule());
    }
}

