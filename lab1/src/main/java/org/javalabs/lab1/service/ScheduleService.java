package org.javalabs.lab1.service;

import org.javalabs.lab1.dao.AuditoryRepository;
import org.javalabs.lab1.dao.ScheduleRepository;
import org.javalabs.lab1.entity.AuditoryEntity;
import org.javalabs.lab1.model.apiresponse.ApiResponse;
import org.javalabs.lab1.model.scheduledto.Schedule;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
import org.javalabs.lab1.entity.ScheduleEntity;

import java.util.List;
import java.util.Optional;


@Service
public class ScheduleService {
    private final ScheduleRepository teacherScheduleRepository;
    private final AuditoryRepository auditoryRepository;

    public ScheduleRepository getTeacherScheduleRepository() {
        return teacherScheduleRepository;
    }

    public ScheduleService(ScheduleRepository teacherScheduleRepository, AuditoryRepository auditoryRepository) {
        this.teacherScheduleRepository = teacherScheduleRepository;
        this.auditoryRepository = auditoryRepository;
    }

    public ApiResponse searchPage(String query) {
        String apiUrl = "https://iis.bsuir.by/api/v1/schedule" +
                "?studentGroup=" + query;

        RestTemplate restTemplate = new RestTemplate();

        ApiResponse response = new ApiResponse();
        response = restTemplate.getForObject(apiUrl, ApiResponse.class);

        if (response != null)
        {

            ScheduleEntity scheduleEntity = teacherScheduleRepository.findByGroupName(
                                                response.getStudentGroupDto().getName());

            if(scheduleEntity == null) {
                scheduleEntity = new ScheduleEntity();
                scheduleEntity.setCourse(response.getStudentGroupDto().getCourse());
                scheduleEntity.setGroupName(response.getStudentGroupDto().getName());
                scheduleEntity.setFacultyId(response.getStudentGroupDto().getFacultyId());
                scheduleEntity.setFacultyAbbrev(response.getStudentGroupDto().getFacultyAbbrev());

                teacherScheduleRepository.save(scheduleEntity);

                for (List<Schedule> scheduleList : response.getSchedules().values()) {
                    for (Schedule schedule : scheduleList) {
                        for (String auditory : schedule.getAuditories()) {
                            AuditoryEntity auditoryEntity = new AuditoryEntity();
                            auditoryEntity.setAuditoryNumber(auditory);
                            auditoryEntity.setGroupName(response.getStudentGroupDto().getName());
                            auditoryEntity.setStartTime(schedule.getStartLessonTime());
                            auditoryEntity.setDate(schedule.getStartLessonDate());
                            auditoryEntity.setSchedule(scheduleEntity);

                            auditoryRepository.save(auditoryEntity);
                        }
                    }
                }
            }
        } else
            return null;

        return response;
    }

    public ScheduleEntity createSchedule(ScheduleEntity scheduleEntity) {
        if (scheduleEntity == null) {
            throw new IllegalArgumentException("error");
        }

        return teacherScheduleRepository.save(scheduleEntity);
    }

    public ScheduleEntity updateSchedule(int id, ScheduleEntity scheduleEntity) {
        if (scheduleEntity == null) {
            throw new IllegalArgumentException("error");
        }

        Optional<ScheduleEntity> existingEntityOptional = teacherScheduleRepository.findById(id);
        if (existingEntityOptional.isEmpty()) {
            throw new IllegalArgumentException("id=" + id + " not found");
        }

        ScheduleEntity existingEntity = existingEntityOptional.get();

        existingEntity.setCourse(scheduleEntity.getCourse());
        existingEntity.setGroupName(scheduleEntity.getGroupName());
        existingEntity.setFacultyId(scheduleEntity.getFacultyId());
        existingEntity.setFacultyAbbrev(scheduleEntity.getFacultyAbbrev());
        return teacherScheduleRepository.save(existingEntity);
    }

    public void deleteSchedule(int id) {
        teacherScheduleRepository.deleteById(id);
    }

}