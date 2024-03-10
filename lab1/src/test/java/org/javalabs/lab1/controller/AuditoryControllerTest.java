package org.javalabs.lab1.controller;


import org.javalabs.lab1.entity.Auditory;
import org.javalabs.lab1.model.auditorydto.AuditoryDto;
import org.javalabs.lab1.service.AuditoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import java.util.ArrayList;
import java.util.List;
import static org.javalabs.lab1.controller.AuditoryController.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuditoryControllerTest {

    @Mock
    private AuditoryService auditoryService;

    @InjectMocks
    private AuditoryController auditoryController;

    @Test
    public void testGetAuditory() {
        int id = 1;
        Auditory expectedAuditory = new Auditory();
        when(auditoryService.getAuditoryById(id)).thenReturn(expectedAuditory);

        ResponseEntity<Auditory> response = auditoryController.getLessonById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedAuditory, response.getBody());
    }

    @Test
    public void testGetAuditoryById_NotFound() {
        int id = 1;
        when(auditoryService.getAuditoryById(id)).thenReturn(null);

        ResponseEntity<Auditory> response = auditoryController.getLessonById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetAuditoriesByDateAndScheduleId() {
        String date = "2024-03-10";
        String groupName = "Group1";
        List<AuditoryDto> expectedAuditories = new ArrayList<>();
        when(auditoryService.getAuditoriesByDateAndScheduleId(date, groupName)).thenReturn(expectedAuditories);

        List<AuditoryDto> actualAuditories = auditoryController.getAuditoriesByDateAndScheduleId(date, groupName);

        assertEquals(expectedAuditories, actualAuditories);
    }

    @Test
    public void testCreateAuditory() {
        Auditory auditoryEntity = new Auditory();
        String group = "Group1";
        when(auditoryService.createAuditory(auditoryEntity, group)).thenReturn(auditoryEntity);

        ResponseEntity<String> response = auditoryController.createAuditory(auditoryEntity, group);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(STATUS_CODE_OK, response.getBody());
    }

    @Test
    public void testCreateAuditory_Error() {
        Auditory auditoryEntity = null;
        String group = "Group1";
        ResponseEntity<String> expectedResponse = ResponseEntity.badRequest().body("error");

        ResponseEntity<String> response = auditoryController.createAuditory(auditoryEntity, group);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testCreateAuditoriesBulk() throws Exception {
        List<Auditory> auditories = new ArrayList<>();
        String group = "Group1";
        doThrow(new Exception("Error message")).when(auditoryService).createAuditoriesBulk(auditories, group);

        ResponseEntity<String> response = auditoryController.createAuditoriesBulk(auditories, group);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error: Error message", response.getBody());
    }

    @Test
    public void testUpdateAuditory() {
        int id = 1;
        Auditory auditoryDto = new Auditory();
        Auditory expectedAuditory = new Auditory();
        when(auditoryService.updateAuditory(id, auditoryDto)).thenReturn(expectedAuditory);
        ResponseEntity<String> response = auditoryController.updateAuditory(id, auditoryDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(STATUS_CODE_OK, response.getBody());
    }

    @Test
    public void testUpdateAuditory_Error() {
        int id = 1;
        Auditory auditoryDto = null;
        ResponseEntity<String> expectedResponse = ResponseEntity.badRequest().body("error");

        ResponseEntity<String> response = auditoryController.updateAuditory(id, auditoryDto);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void testHandleMissingParameter() {
        String expectedErrorMessage = "Required parameter is missing";

        String errorMessage = auditoryController.handleMissingParameter(new MissingServletRequestParameterException("param", "String"));

        assertEquals(expectedErrorMessage, errorMessage);
    }
}