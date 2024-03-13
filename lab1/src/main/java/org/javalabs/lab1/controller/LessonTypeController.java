package org.javalabs.lab1.controller;

import org.javalabs.lab1.entity.LessonType;
import org.javalabs.lab1.exceptionhandler.GlobalExceptionHandler;
import org.javalabs.lab1.service.LessonTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LessonTypeController {
    private final LessonTypeService lessonTypeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(LessonTypeController.class);


    public LessonTypeController(LessonTypeService lessonTypeService) {
        this.lessonTypeService = lessonTypeService;
    }

    @PostMapping("/lesson-types")
    public ResponseEntity<LessonType> createLessonType(@RequestBody LessonType lessonType) {
        LOGGER.info("post endpoint /lesson-types was called");

        if (lessonType == null || lessonType.getId() == 0) {
            throw new IllegalArgumentException("Invalid lesson type data");
        }

        LessonType createdLessonType = lessonTypeService.createLessonType(lessonType);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLessonType);
    }

    @GetMapping("/lesson-types")
    public ResponseEntity<List<LessonType>> getAllLessonTypes() {
        LOGGER.info("get endpoint /lesson-types was called");

        List<LessonType> lessonTypes = lessonTypeService.getAllLessonTypes();
        return ResponseEntity.ok(lessonTypes);
    }

    @GetMapping("/lesson-types/{id}")
    public ResponseEntity<LessonType> getLessonTypeById(@PathVariable int id) {
        LOGGER.info("get endpoint /lesson-types/{id} was called");

        LessonType lessonType = lessonTypeService.getLessonTypeById(id);
        return ResponseEntity.ok(lessonType);
    }

    @DeleteMapping("/lesson-types/{id}")
    public ResponseEntity<Void> deleteLessonType(@PathVariable int id) {
        LOGGER.info("delete endpoint /lesson-types/{id} was called");

        try {
            lessonTypeService.deleteLessonType(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
