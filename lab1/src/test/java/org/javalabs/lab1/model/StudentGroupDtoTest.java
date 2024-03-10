package org.javalabs.lab1.model;

import org.javalabs.lab1.model.studentgroupdto.StudentGroupDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentGroupDtoTest {

    @Test
    public void testGetterAndSetter() {
        StudentGroupDto studentGroupDto = new StudentGroupDto();
        String name = "Group 1";
        int facultyId = 1;
        String facultyAbbrev = "FAC";
        int specialityDepartmentEducationFormId = 123;
        String specialityName = "Computer Science";
        String specialityAbbrev = "CS";
        int course = 2;
        int id = 456;
        String calendarId = "CAL123";
        int educationDegree = 3;

        studentGroupDto.setName(name);
        studentGroupDto.setFacultyId(facultyId);
        studentGroupDto.setFacultyAbbrev(facultyAbbrev);
        studentGroupDto.setSpecialityDepartmentEducationFormId(specialityDepartmentEducationFormId);
        studentGroupDto.setSpecialityName(specialityName);
        studentGroupDto.setSpecialityAbbrev(specialityAbbrev);
        studentGroupDto.setCourse(course);
        studentGroupDto.setId(id);
        studentGroupDto.setCalendarId(calendarId);
        studentGroupDto.setEducationDegree(educationDegree);

        assertEquals(name, studentGroupDto.getName());
        assertEquals(facultyId, studentGroupDto.getFacultyId());
        assertEquals(facultyAbbrev, studentGroupDto.getFacultyAbbrev());
        assertEquals(specialityDepartmentEducationFormId, studentGroupDto.getSpecialityDepartmentEducationFormId());
        assertEquals(specialityName, studentGroupDto.getSpecialityName());
        assertEquals(specialityAbbrev, studentGroupDto.getSpecialityAbbrev());
        assertEquals(course, studentGroupDto.getCourse());
        assertEquals(id, studentGroupDto.getId());
        assertEquals(calendarId, studentGroupDto.getCalendarId());
        assertEquals(educationDegree, studentGroupDto.getEducationDegree());
    }
}
