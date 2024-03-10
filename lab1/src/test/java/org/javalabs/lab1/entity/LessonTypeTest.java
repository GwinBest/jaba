package org.javalabs.lab1.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
 class LessonTypeTest {

    @Test
     void testGettersAndSetters() {
        LessonType lessonType = new LessonType();
        lessonType.setId(1);
        lessonType.setType("Lecture");

        assertEquals(1, lessonType.getId());
        assertEquals("Lecture", lessonType.getType());
    }

    @Test
     void testEmptyConstructor() {
        LessonType lessonType = new LessonType();
        assertEquals(0, lessonType.getId());
        assertNull(lessonType.getType());
    }
}
