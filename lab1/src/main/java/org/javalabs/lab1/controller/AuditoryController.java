package org.javalabs.lab1.controller;

import org.javalabs.lab1.entity.Auditory;
import org.javalabs.lab1.model.auditorydto.AuditoryDto;
import org.javalabs.lab1.service.AuditoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.List;

@RestController
public class AuditoryController {
    private final AuditoryService auditoryService;
    private static final String STATUS_CODE_OK = "success";

    public AuditoryController(AuditoryService service) {
        this.auditoryService = service;
    }

    @GetMapping("/auditory/{id}")
    public ResponseEntity<Auditory> getLessonById(@PathVariable int id) {
        Auditory auditoryEntity = auditoryService.getAuditoryById(id);

        if (auditoryEntity != null) {
            return ResponseEntity.ok(auditoryEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/useful")
    public List<AuditoryDto> getAuditoriesByDateAndScheduleId(
            @RequestParam("date") String date,
            @RequestParam("groupName") String groupName) {
        return auditoryService.getAuditoriesByDateAndScheduleId(date,groupName);
    }

    @PostMapping("/auditory/{group}")
    public ResponseEntity<String> createAuditory(@RequestBody Auditory auditoryEntity,
                                                 @PathVariable("group") String group) {
        if (auditoryEntity == null) {
            return ResponseEntity.badRequest().body("error");
        }

        try {
            auditoryService.createAuditory(auditoryEntity, group);
            return ResponseEntity.ok(STATUS_CODE_OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error: " + e.getMessage());
        }
    }

    @PutMapping("/auditory/{id}")
    public ResponseEntity<String> updateAuditory(@PathVariable("id") int id,
                                                 @RequestBody Auditory auditoryDto) {
        if (auditoryDto == null) {
            return ResponseEntity.badRequest().body("error");
        }

        try {
            auditoryService.updateAuditory(id, auditoryDto);
            return ResponseEntity.ok(STATUS_CODE_OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error " + e.getMessage());
        }
    }

    @DeleteMapping("/auditory/{id}")
    public ResponseEntity<String> deleteAuditory(@PathVariable("id") int id) {
        try {
            auditoryService.deleteAuditory(id);
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
