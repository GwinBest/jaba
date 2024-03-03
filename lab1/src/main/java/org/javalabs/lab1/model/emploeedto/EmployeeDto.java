package org.javalabs.lab1.model.emploeedto;

import java.util.List;

public class EmployeeDto {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String photoLink;
    private String degree;
    private String degreeAbbrev;
    private String email;
    private String urlId;
    private String calendarId;
    private List<String> jobPositions;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public String getDegree() {
        return degree;
    }

    public String getDegreeAbbrev() {
        return degreeAbbrev;
    }

    public String getEmail() {
        return email;
    }

    public String getUrlId() {
        return urlId;
    }

    public String getCalendarId() {
        return calendarId;
    }

    public List<String> getJobPositions() {
        return jobPositions;
    }

}