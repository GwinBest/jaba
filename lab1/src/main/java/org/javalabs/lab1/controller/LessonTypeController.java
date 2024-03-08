package org.javalabs.lab1.controller;

import org.javalabs.lab1.entity.LessonType;
import org.javalabs.lab1.service.LessonTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LessonTypeController {
    private final LessonTypeService lessonTypeService;

    public LessonTypeController(LessonTypeService lessonTypeService) {
        this.lessonTypeService = lessonTypeService;
    }

    @PostMapping("/lesson-types")
    public ResponseEntity<LessonType> createLessonType(@RequestBody LessonType lessonType) {
        LessonType createdLessonType = lessonTypeService.createLessonType(lessonType);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLessonType);
    }

    @GetMapping("/lesson-types")
    public ResponseEntity<List<LessonType>> getAllLessonTypes() {
        List<LessonType> lessonTypes = lessonTypeService.getAllLessonTypes();
        return ResponseEntity.ok(lessonTypes);
    }

    @GetMapping("/lesson-types/{id}")
    public ResponseEntity<LessonType> getLessonTypeById(@PathVariable int id) {
        LessonType lessonType = lessonTypeService.getLessonTypeById(id);
        return ResponseEntity.ok(lessonType);
    }

    @DeleteMapping("/lesson-types/{id}")
    public ResponseEntity<Void> deleteLessonType(@PathVariable int id) {
        lessonTypeService.deleteLessonType(id);
        return ResponseEntity.noContent().build();
    }
}
