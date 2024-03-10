package org.javalabs.lab1.service;

import org.javalabs.lab1.dao.LessonTypeRepository;
import org.javalabs.lab1.entity.LessonType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class LessonTypeServiceTest {

    @Mock
    private LessonTypeRepository lessonTypeRepository;

    @InjectMocks
    private LessonTypeService lessonTypeService;

    @Test
     void testCreateLessonType() {
        LessonType lessonType = new LessonType();
        when(lessonTypeRepository.save(lessonType)).thenReturn(lessonType);

        LessonType createdLessonType = lessonTypeService.createLessonType(lessonType);

        assertNotNull(createdLessonType);
        assertEquals(lessonType, createdLessonType);
    }

    @Test
     void testGetAllLessonTypes() {
        List<LessonType> expectedLessonTypes = new ArrayList<>();
        when(lessonTypeRepository.findAll()).thenReturn(expectedLessonTypes);

        List<LessonType> actualLessonTypes = lessonTypeService.getAllLessonTypes();

        assertEquals(expectedLessonTypes, actualLessonTypes);
    }

    @Test
     void testGetLessonTypeById_ExistingId() {
        int id = 1;
        LessonType expectedLessonType = new LessonType();
        when(lessonTypeRepository.findById(id)).thenReturn(Optional.of(expectedLessonType));

        LessonType actualLessonType = lessonTypeService.getLessonTypeById(id);

        assertEquals(expectedLessonType, actualLessonType);
    }

    @Test
     void testGetLessonTypeById_NonExistingId() {
        int id = 1;
        when(lessonTypeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> lessonTypeService.getLessonTypeById(id));
    }

    @Test
     void testDeleteLessonType() {
        int id = 1;
        doNothing().when(lessonTypeRepository).deleteById(id);

        assertDoesNotThrow(() -> lessonTypeService.deleteLessonType(id));
    }
}