package org.javalabs.lab1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "info_about_request")
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;
    @Column(name = "groupName")
    private String groupName;
    @Column(name = "facultyId")
    private int facultyId;
    @Column(name = "facultyAbbrev")
    private String facultyAbbrev;
    @Column(name = "course")
    private int course;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyAbbrev() {
        return facultyAbbrev;
    }

    public void setFacultyAbbrev(String facultyAbbrev) {
        this.facultyAbbrev = facultyAbbrev;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}

