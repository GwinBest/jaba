package org.javalabs.lab1.controller;

import org.javalabs.lab1.entity.Auditory;
import org.javalabs.lab1.exceptionhandler.GlobalExceptionHandler;
import org.javalabs.lab1.model.auditorydto.AuditoryDto;
import org.javalabs.lab1.service.AuditoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.List;

@RestController
public class AuditoryController {
    private final AuditoryService auditoryService;
    private static final String STATUS_CODE_OK = "success";
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    public AuditoryController(AuditoryService service) {
        this.auditoryService = service;
    }

    @GetMapping("/auditory/{id}")
    public ResponseEntity<Auditory> getLessonById(@PathVariable int id) {
        LOGGER.info("get endpoint /auditory/{id} was called");
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
        LOGGER.info("get endpoint /useful was called");

        return auditoryService.getAuditoriesByDateAndScheduleId(date,groupName);
    }

    @PostMapping("/auditory/{group}")
    public ResponseEntity<String> createAuditory(@RequestBody Auditory auditoryEntity,
                                                 @PathVariable("group") String group) {
        LOGGER.info("post endpoint /auditory/{group} was called");

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
        LOGGER.info("put endpoint /auditory/{id} was called");

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
        LOGGER.info("delete endpoint /auditory/{id} was called");

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
