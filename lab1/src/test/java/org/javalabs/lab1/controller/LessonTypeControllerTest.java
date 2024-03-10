package org.javalabs.lab1.controller;

import org.javalabs.lab1.entity.LessonType;
import org.javalabs.lab1.service.LessonTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LessonTypeControllerTest {

    private LessonTypeService lessonTypeService;
    private LessonTypeController lessonTypeController;

    @BeforeEach
    void setUp() {
        lessonTypeService = mock(LessonTypeService.class);
        lessonTypeController = new LessonTypeController(lessonTypeService);
    }

    @Test
    void testCreateLessonType() {
        LessonType lessonType = new LessonType();
        lessonType.setId(1);
        when(lessonTypeService.createLessonType(lessonType)).thenReturn(lessonType);

        ResponseEntity<LessonType> response = lessonTypeController.createLessonType(lessonType);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(lessonType, response.getBody());
    }

    @Test
    void testCreateLessonType_InvalidData() {
        LessonType lessonType = new LessonType(); // Создайте недопустимые данные
        lessonType.setId(0); // Например, недопустимый ID

        try {
            lessonTypeController.createLessonType(lessonType);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid lesson type data", e.getMessage());
        }
    }

    @Test
    void testGetAllLessonTypes() {
        List<LessonType> lessonTypes = new ArrayList<>();
        when(lessonTypeService.getAllLessonTypes()).thenReturn(lessonTypes);

        ResponseEntity<List<LessonType>> response = lessonTypeController.getAllLessonTypes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lessonTypes, response.getBody());
    }

    @Test
    void testGetLessonTypeById() {
        int id = 1;
        LessonType lessonType = new LessonType();
        when(lessonTypeService.getLessonTypeById(id)).thenReturn(lessonType);

        ResponseEntity<LessonType> response = lessonTypeController.getLessonTypeById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lessonType, response.getBody());
    }

    @Test
    void testDeleteLessonType() {
        int id = 1;
        doNothing().when(lessonTypeService).deleteLessonType(id);

        ResponseEntity<Void> response = lessonTypeController.deleteLessonType(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteLessonType_NotFound() {
        int id = 1;
        doThrow(new IllegalArgumentException()).when(lessonTypeService).deleteLessonType(id);

        ResponseEntity<Void> response = lessonTypeController.deleteLessonType(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
