package org.javalabs.lab1.service;

import  org.javalabs.lab1.dao.MyRepository;
import org.javalabs.lab1.model.apiresponse.ApiResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.stereotype.Service;
import  org.javalabs.lab1.entity.ScheduleEntity;

@Service
public class MyService{

    private final MyRepository teacherScheduleRepository;

    public MyService(MyRepository teacherScheduleRepository) {
        this.teacherScheduleRepository = teacherScheduleRepository;
    }
    public ApiResponse searchPage(String query) {
        String apiUrl = "https://iis.bsuir.by/api/v1/schedule" +
                "?studentGroup=" + query;

        RestTemplate restTemplate = new RestTemplate();

        ApiResponse response = new ApiResponse();
        response = restTemplate.getForObject(apiUrl, ApiResponse.class);

        if (response != null)
        {
            ScheduleEntity scheduleEntity = new ScheduleEntity();
            scheduleEntity.setCourse(response.getStudentGroupDto().getCourse());
            scheduleEntity.setGroupName(response.getStudentGroupDto().getName());
            scheduleEntity.setFacultyId(response.getStudentGroupDto().getFacultyId());
            scheduleEntity.setFacultyAbbrev(response.getStudentGroupDto().getFacultyAbbrev());

            teacherScheduleRepository.save(scheduleEntity);
        } else
            return null;

        try {
            return response;
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }
}