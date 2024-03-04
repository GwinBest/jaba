package org.javalabs.lab1.controller;

import org.javalabs.lab1.entity.AuditoryEntity;
import org.javalabs.lab1.service.AuditoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MissingServletRequestParameterException;

@RestController
public class AuditoryController {
    private final AuditoryService service;
    private static final String STATUS_CODE_OK = "success";


    public AuditoryController(AuditoryService service) {
        this.service = service;
    }

    @GetMapping("/auditory/{id}")
    public ResponseEntity<AuditoryEntity> getLessonById(@PathVariable int id) {
        AuditoryEntity auditoryEntity = service.getAuditoryById(id);

        if (auditoryEntity != null) {
            return ResponseEntity.ok(auditoryEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/auditory/{group}")
    public ResponseEntity<String> createAuditory(@RequestBody AuditoryEntity auditoryEntity,
                                                 @PathVariable("group") String group) {
        if (auditoryEntity == null) {
            return ResponseEntity.badRequest().body("error");
        }

        try {
            service.createAuditory(auditoryEntity, group);
            return ResponseEntity.ok(STATUS_CODE_OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error: " + e.getMessage());
        }
    }

    @PutMapping("/auditory/{id}")
    public ResponseEntity<String> updateAuditory(@PathVariable("id") int id,
                                                 @RequestBody AuditoryEntity auditoryDto) {
        if (auditoryDto == null) {
            return ResponseEntity.badRequest().body("error");
        }

        try {
            service.updateAuditory(id, auditoryDto);
            return ResponseEntity.ok(STATUS_CODE_OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error " + e.getMessage());
        }
    }

    @DeleteMapping("/auditory/{id}")
    public ResponseEntity<String> deleteAuditory(@PathVariable("id") int id) {
        try {
            service.deleteAuditory(id);
            return ResponseEntity.ok(STATUS_CODE_OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParameter(MissingServletRequestParameterException ex) {
        return "Required parameter is missing";
    }
}
