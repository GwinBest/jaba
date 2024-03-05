package org.javalabs.lab1.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "schedule")
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

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "schedule")
    private List<AuditoryEntity> auditoryEntities;

    public void setAuditoryEntities(List<AuditoryEntity> auditoryEntities) {
        this.auditoryEntities = auditoryEntities;
    }

    public List<AuditoryEntity> getAuditoryEntities() {
        return auditoryEntities;
    }

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

