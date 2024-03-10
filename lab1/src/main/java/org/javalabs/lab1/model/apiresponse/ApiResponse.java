package org.javalabs.lab1.model.apiresponse;

import java.util.List;
import java.util.Map;
import org.javalabs.lab1.model.scheduledto.Schedule;
import org.javalabs.lab1.model.studentgroupdto.StudentGroupDto;

public class ApiResponse {
    private Object employeeDto;
    private StudentGroupDto studentGroupDto;
    private Map<String, List<Schedule>> schedules;
    private List<Schedule> exams;
    private String startDate;
    private String endDate;
    private String startExamsDate;
    private String endExamsDate;

    public void setSchedules(Map<String, List<Schedule>> schedules) {
        this.schedules = schedules;
    }

    public void setEmployeeDto(Object employeeDto) {
        this.employeeDto = employeeDto;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setEndExamsDate(String endExamsDate) {
        this.endExamsDate = endExamsDate;
    }

    public void setExams(List<Schedule> exams) {
        this.exams = exams;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setStartExamsDate(String startExamsDate) {
        this.startExamsDate = startExamsDate;
    }

    public void setStudentGroupDto(StudentGroupDto studentGroupDto) {
        this.studentGroupDto = studentGroupDto;
    }

    public Object getEmployeeDto() {
        return employeeDto;
    }

    public StudentGroupDto getStudentGroupDto() {
        return studentGroupDto;
    }

    public Map<String, List<Schedule>> getSchedules() {
        return schedules;
    }

    public List<Schedule> getExams() {
        return exams;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartExamsDate() {
        return startExamsDate;
    }

    public String getEndExamsDate() {
        return endExamsDate;
    }
}