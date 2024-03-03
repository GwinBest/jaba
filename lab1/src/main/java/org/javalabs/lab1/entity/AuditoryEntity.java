package org.javalabs.lab1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "auditory")
public class AuditoryEntity {

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
    private ScheduleEntity schedule;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSchedule(ScheduleEntity schedule) {
        this.schedule = schedule;
    }

    public ScheduleEntity getSchedule() {
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
