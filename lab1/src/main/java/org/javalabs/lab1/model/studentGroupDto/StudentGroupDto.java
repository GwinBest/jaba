package org.javalabs.lab1.model.studentGroupDto;

public class StudentGroupDto {
    private String name;
    private int facultyId;
    private String facultyAbbrev;
    private int specialityDepartmentEducationFormId;
    private String specialityName;
    private String specialityAbbrev;
    private int course;
    private int id;
    private String calendarId;
    private int educationDegree;

    public String getName() {
        return name;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public String getFacultyAbbrev() {
        return facultyAbbrev;
    }

    public int getSpecialityDepartmentEducationFormId() {
        return specialityDepartmentEducationFormId;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public String getSpecialityAbbrev() {
        return specialityAbbrev;
    }

    public int getCourse() {
        return course;
    }

    public int getId() {
        return id;
    }

    public String getCalendarId() {
        return calendarId;
    }

    public int getEducationDegree() {
        return educationDegree;
    }

}