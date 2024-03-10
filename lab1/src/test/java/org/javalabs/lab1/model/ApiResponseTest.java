package org.javalabs.lab1.model;

import org.javalabs.lab1.model.apiresponse.ApiResponse;
import org.junit.jupiter.api.Test;
import org.javalabs.lab1.model.scheduledto.Schedule;
import org.javalabs.lab1.model.studentgroupdto.StudentGroupDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiResponseTest {

    @Test
    public void testSetterAndGetter() {
        ApiResponse apiResponse = new ApiResponse();
        Object employeeDto = new Object();
        StudentGroupDto studentGroupDto = new StudentGroupDto();
        Map<String, List<Schedule>> schedules = new HashMap<>();
        List<Schedule> exams = new ArrayList<>();
        String startDate = "2024-03-10";
        String endDate = "2024-03-20";
        String startExamsDate = "2024-03-15";
        String endExamsDate = "2024-03-25";

        apiResponse.setEmployeeDto(employeeDto);
        apiResponse.setStudentGroupDto(studentGroupDto);
        apiResponse.setSchedules(schedules);
        apiResponse.setExams(exams);
        apiResponse.setStartDate(startDate);
        apiResponse.setEndDate(endDate);
        apiResponse.setStartExamsDate(startExamsDate);
        apiResponse.setEndExamsDate(endExamsDate);

        assertEquals(employeeDto, apiResponse.getEmployeeDto());
        assertEquals(studentGroupDto, apiResponse.getStudentGroupDto());
        assertEquals(schedules, apiResponse.getSchedules());
        assertEquals(exams, apiResponse.getExams());
        assertEquals(startDate, apiResponse.getStartDate());
        assertEquals(endDate, apiResponse.getEndDate());
        assertEquals(startExamsDate, apiResponse.getStartExamsDate());
        assertEquals(endExamsDate, apiResponse.getEndExamsDate());
    }

}
