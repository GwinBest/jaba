package org.javalabs.lab1.service;

import org.javalabs.lab1.dao.LessonTypeRepository;
import org.javalabs.lab1.entity.LessonType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonTypeService {
    private final LessonTypeRepository lessonTypeRepository;

    public LessonTypeService(LessonTypeRepository lessonTypeRepository) {
        this.lessonTypeRepository = lessonTypeRepository;
    }

    public LessonType createLessonType(LessonType lessonType) {
        return lessonTypeRepository.save(lessonType);
    }

    public List<LessonType> getAllLessonTypes() {
        return lessonTypeRepository.findAll();
    }

    public LessonType getLessonTypeById(int id) {
        return lessonTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lesson Type not found"));
    }

    public void deleteLessonType(int id) {
        lessonTypeRepository.deleteById(id);
    }
}
