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

    public void setId(int id) {
        this.id = id;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setDegreeAbbrev(String degreeAbbrev) {
        this.degreeAbbrev = degreeAbbrev;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setJobPositions(List<String> jobPositions) {
        this.jobPositions = jobPositions;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

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