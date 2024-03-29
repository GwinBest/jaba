package org.javalabs.lab1.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "auditory")
public class Auditory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "auditory_number")
    private String auditoryNumber;

    @Column(name = "groupName")
    private String groupName;

    @Column(name = "StartTime")
    private String startTime;

    @Column(name = "Date")
    private String date;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToMany
    @JoinTable(
            name = "auditoryLessonType",
            joinColumns = @JoinColumn(name = "auditoryId"),
            inverseJoinColumns = @JoinColumn(name = "lessonTypeId")
    )
    private List<LessonType> lessonTypes = new ArrayList<>();

    public void setLessonTypes(List<LessonType> lessonTypes) {
        this.lessonTypes = lessonTypes;
    }

    public List<LessonType> getLessonTypes() {
        return lessonTypes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAuditoryNumber() {
        return auditoryNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuditoryNumber(String auditoryNumber) {
        this.auditoryNumber = auditoryNumber;
    }

}
