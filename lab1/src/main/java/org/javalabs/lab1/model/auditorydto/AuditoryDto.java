package org.javalabs.lab1.model.auditorydto;

public class AuditoryDto {
    private String auditoryNumber;
    private String startTime;
    private String groupName;

    public AuditoryDto(String auditoryNumber, String startTime, String groupName) {
        this.auditoryNumber = auditoryNumber;
        this.startTime = startTime;
        this.groupName = groupName;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getAuditoryNumber() {
        return auditoryNumber;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setAuditoryNumber(String auditoryNumber) {
        this.auditoryNumber = auditoryNumber;
    }
}
