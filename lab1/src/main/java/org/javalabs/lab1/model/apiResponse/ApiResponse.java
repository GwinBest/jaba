package org.javalabs.lab1.model.apiResponse;

import org.javalabs.lab1.model.scheduleDto.Schedule;
import org.javalabs.lab1.model.studentGroupDto.StudentGroupDto;

import java.util.List;
import java.util.Map;

public class ApiResponse {
    private Object employeeDto;
    private StudentGroupDto studentGroupDto;
    private Map<String, List<Schedule>> schedules;
    private List<Schedule> exams;
    private String startDate;
    private String endDate;
    private String startExamsDate;
    private String endExamsDate;

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