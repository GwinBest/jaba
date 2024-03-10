package org.javalabs.lab1.model;

import org.javalabs.lab1.model.auditorydto.AuditoryDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuditoryDtoTest {

    @Test
    void testConstructorAndGetters() {
        String auditoryNumber = "A101";
        String startTime = "9:00";
        String groupName = "Group A";

        AuditoryDto auditoryDto = new AuditoryDto(auditoryNumber, startTime, groupName);

        assertEquals(auditoryNumber, auditoryDto.getAuditoryNumber());
        assertEquals(startTime, auditoryDto.getStartTime());
        assertEquals(groupName, auditoryDto.getGroupName());
    }

    @Test
    void testSetters() {
        String auditoryNumber = "A101";
        String startTime = "9:00";
        String groupName = "Group A";

        AuditoryDto auditoryDto = new AuditoryDto("", "", "");

        auditoryDto.setAuditoryNumber(auditoryNumber);
        auditoryDto.setStartTime(startTime);
        auditoryDto.setGroupName(groupName);

        assertEquals(auditoryNumber, auditoryDto.getAuditoryNumber());
        assertEquals(startTime, auditoryDto.getStartTime());
        assertEquals(groupName, auditoryDto.getGroupName());
    }
}
