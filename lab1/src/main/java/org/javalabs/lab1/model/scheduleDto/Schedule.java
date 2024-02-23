package org.javalabs.lab1.model.scheduleDto;

import org.javalabs.lab1.model.emploeeDto.EmployeeDto;
import org.javalabs.lab1.model.studentGroupDto.StudentGroupDto;

import java.util.List;

public class Schedule {
    private List<String> auditories;
    private String endLessonTime;
    private String lessonTypeAbbrev;
    private String note;
    private int numSubgroup;
    private String startLessonTime;
    private List<StudentGroupDto> studentGroups;
    private String subject;
    private String subjectFullName;
    private List<Integer> weekNumber;
    private List<EmployeeDto> employees;
    private String dateLesson;
    private String startLessonDate;
    private String endLessonDate;
    private boolean announcement;
    private boolean split;

    public List<String> getAuditories() {
        return auditories;
    }

    public String getEndLessonTime() {
        return endLessonTime;
    }

    public String getLessonTypeAbbrev() {
        return lessonTypeAbbrev;
    }

    public String getNote() {
        return note;
    }

    public int getNumSubgroup() {
        return numSubgroup;
    }

    public String getStartLessonTime() {
        return startLessonTime;
    }

    public List<StudentGroupDto> getStudentGroups() {
        return studentGroups;
    }

    public String getSubject() {
        return subject;
    }

    public String getSubjectFullName() {
        return subjectFullName;
    }

    public List<Integer> getWeekNumber() {
        return weekNumber;
    }

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public String getDateLesson() {
        return dateLesson;
    }

    public String getStartLessonDate() {
        return startLessonDate;
    }

    public String getEndLessonDate() {
        return endLessonDate;
    }

    public boolean isAnnouncement() {
        return announcement;
    }

    public boolean isSplit() {
        return split;
    }

}