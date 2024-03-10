package org.javalabs.lab1.model;

import org.javalabs.lab1.model.emploeedto.EmployeeDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class EmployeeDtoTest {

    @Test
    void testGetters() {
        EmployeeDto employeeDto = new EmployeeDto();
        int id = 1;
        String firstName = "John";
        String middleName = "Doe";
        String lastName = "Smith";
        String photoLink = "https://example.com/photo.jpg";
        String degree = "PhD";
        String degreeAbbrev = "Dr.";
        String email = "john@example.com";
        String urlId = "johnsmith";
        String calendarId = "johnsmith@example.com";
        List<String> jobPositions = new ArrayList<>();
        jobPositions.add("Professor");
        jobPositions.add("Researcher");

        employeeDto.setId(id);
        employeeDto.setFirstName(firstName);
        employeeDto.setMiddleName(middleName);
        employeeDto.setLastName(lastName);
        employeeDto.setPhotoLink(photoLink);
        employeeDto.setDegree(degree);
        employeeDto.setDegreeAbbrev(degreeAbbrev);
        employeeDto.setEmail(email);
        employeeDto.setUrlId(urlId);
        employeeDto.setCalendarId(calendarId);
        employeeDto.setJobPositions(jobPositions);

        assertEquals(id, employeeDto.getId());
        assertEquals(firstName, employeeDto.getFirstName());
        assertEquals(middleName, employeeDto.getMiddleName());
        assertEquals(lastName, employeeDto.getLastName());
        assertEquals(photoLink, employeeDto.getPhotoLink());
        assertEquals(degree, employeeDto.getDegree());
        assertEquals(degreeAbbrev, employeeDto.getDegreeAbbrev());
        assertEquals(email, employeeDto.getEmail());
        assertEquals(urlId, employeeDto.getUrlId());
        assertEquals(calendarId, employeeDto.getCalendarId());
        assertEquals(jobPositions, employeeDto.getJobPositions());
    }
}
