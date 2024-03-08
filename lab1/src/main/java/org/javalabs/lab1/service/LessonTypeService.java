package org.javalabs.lab1.service;

import org.javalabs.lab1.dao.LessonTypeRepository;
import org.javalabs.lab1.entity.LessonType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonTypeService {
    private final LessonTypeRepository LESSON_TYPE_REPOSITORY;

    public LessonTypeService(LessonTypeRepository lessonTypeRepository) {
        this.LESSON_TYPE_REPOSITORY = lessonTypeRepository;
    }

    public LessonType createLessonType(LessonType lessonType) {
        return LESSON_TYPE_REPOSITORY.save(lessonType);
    }

    public List<LessonType> getAllLessonTypes() {
        return LESSON_TYPE_REPOSITORY.findAll();
    }

    public LessonType getLessonTypeById(int id) {
        return LESSON_TYPE_REPOSITORY.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lesson Type not found"));
    }

    public void deleteLessonType(int id) {
        LESSON_TYPE_REPOSITORY.deleteById(id);
    }
}
