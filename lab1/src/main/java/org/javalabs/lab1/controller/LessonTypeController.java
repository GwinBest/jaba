package org.javalabs.lab1.controller;

import org.javalabs.lab1.entity.LessonType;
import org.javalabs.lab1.service.LessonTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LessonTypeController {
    private final LessonTypeService LESSON_TYPE_SERVICE;

    public LessonTypeController(LessonTypeService lessonTypeService) {
        this.LESSON_TYPE_SERVICE = lessonTypeService;
    }

    @PostMapping("/lesson-types")
    public ResponseEntity<LessonType> createLessonType(@RequestBody LessonType lessonType) {
        LessonType createdLessonType = LESSON_TYPE_SERVICE.createLessonType(lessonType);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLessonType);
    }

    @GetMapping("/lesson-types")
    public ResponseEntity<List<LessonType>> getAllLessonTypes() {
        List<LessonType> lessonTypes = LESSON_TYPE_SERVICE.getAllLessonTypes();
        return ResponseEntity.ok(lessonTypes);
    }

    @GetMapping("/lesson-types/{id}")
    public ResponseEntity<LessonType> getLessonTypeById(@PathVariable int id) {
        LessonType lessonType = LESSON_TYPE_SERVICE.getLessonTypeById(id);
        return ResponseEntity.ok(lessonType);
    }

    @DeleteMapping("/lesson-types/{id}")
    public ResponseEntity<Void> deleteLessonType(@PathVariable int id) {
        LESSON_TYPE_SERVICE.deleteLessonType(id);
        return ResponseEntity.noContent().build();
    }
}
