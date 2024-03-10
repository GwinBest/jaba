package org.javalabs.lab1.model.studentgroupdto;

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

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setEducationDegree(int educationDegree) {
        this.educationDegree = educationDegree;
    }

    public void setFacultyAbbrev(String facultyAbbrev) {
        this.facultyAbbrev = facultyAbbrev;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialityAbbrev(String specialityAbbrev) {
        this.specialityAbbrev = specialityAbbrev;
    }

    public void setSpecialityDepartmentEducationFormId(int specialityDepartmentEducationFormId) {
        this.specialityDepartmentEducationFormId = specialityDepartmentEducationFormId;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
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