package org.javalabs.lab1.model;

import org.javalabs.lab1.model.emploeedto.EmployeeDto;
import org.javalabs.lab1.model.scheduledto.Schedule;
import org.javalabs.lab1.model.studentgroupdto.StudentGroupDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class ScheduleTest {

    @Test
    void testGetters() {
        List<String> auditories = new ArrayList<>();
        auditories.add("A101");
        auditories.add("B102");

        String endLessonTime = "10:30";
        String lessonTypeAbbrev = "Lec";
        String note = "Note";
        int numSubgroup = 1;
        String startLessonTime = "9:00";

        List<StudentGroupDto> studentGroups = new ArrayList<>();

        String subject = "Math";
        String subjectFullName = "Mathematics";

        List<Integer> weekNumber = new ArrayList<>();
        weekNumber.add(1);
        weekNumber.add(2);

        List<EmployeeDto> employees = new ArrayList<>();

        String dateLesson = "2024-03-10";
        String startLessonDate = "2024-03-10";
        String endLessonDate = "2024-03-10";
        boolean announcement = true;
        boolean split = false;

        Schedule schedule = new Schedule();
        schedule.setAuditories(auditories);
        schedule.setEndLessonTime(endLessonTime);
        schedule.setLessonTypeAbbrev(lessonTypeAbbrev);
        schedule.setNote(note);
        schedule.setNumSubgroup(numSubgroup);
        schedule.setStartLessonTime(startLessonTime);
        schedule.setStudentGroups(studentGroups);
        schedule.setSubject(subject);
        schedule.setSubjectFullName(subjectFullName);
        schedule.setWeekNumber(weekNumber);
        schedule.setEmployees(employees);
        schedule.setDateLesson(dateLesson);
        schedule.setStartLessonDate(startLessonDate);
        schedule.setEndLessonDate(endLessonDate);
        schedule.setAnnouncement(announcement);
        schedule.setSplit(split);

        assertEquals(auditories, schedule.getAuditories());
        assertEquals(endLessonTime, schedule.getEndLessonTime());
        assertEquals(lessonTypeAbbrev, schedule.getLessonTypeAbbrev());
        assertEquals(note, schedule.getNote());
        assertEquals(numSubgroup, schedule.getNumSubgroup());
        assertEquals(startLessonTime, schedule.getStartLessonTime());
        assertEquals(studentGroups, schedule.getStudentGroups());
        assertEquals(subject, schedule.getSubject());
        assertEquals(subjectFullName, schedule.getSubjectFullName());
        assertEquals(weekNumber, schedule.getWeekNumber());
        assertEquals(employees, schedule.getEmployees());
        assertEquals(dateLesson, schedule.getDateLesson());
        assertEquals(startLessonDate, schedule.getStartLessonDate());
        assertEquals(endLessonDate, schedule.getEndLessonDate());
        assertEquals(announcement, schedule.isAnnouncement());
        assertEquals(split, schedule.isSplit());
    }
}
