package org.javalabs.lab1.service;

import org.javalabs.lab1.dao.AuditoryRepository;
import org.javalabs.lab1.dao.LessonTypeRepository;
import org.javalabs.lab1.dao.ScheduleRepository;
import org.javalabs.lab1.entity.Auditory;
import org.javalabs.lab1.entity.LessonType;
import org.javalabs.lab1.model.apiresponse.ApiResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
import org.javalabs.lab1.entity.Schedule;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    private final ScheduleRepository SCHEDULE_REPOSITORY;
    private final AuditoryRepository AUDITORY_REPOSITORY;
    private final LessonTypeRepository LESSON_TYPE_REPOSITORY;

    public ScheduleRepository getTeacherScheduleRepository() {
        return SCHEDULE_REPOSITORY;
    }

    public ScheduleService(ScheduleRepository teacherScheduleRepository, AuditoryRepository auditoryRepository,
                           LessonTypeRepository lessonTypeRepository) {
        this.SCHEDULE_REPOSITORY = teacherScheduleRepository;
        this.AUDITORY_REPOSITORY = auditoryRepository;
        this.LESSON_TYPE_REPOSITORY = lessonTypeRepository;
    }

    public ApiResponse searchPage(String query) {
        String apiUrl = "https://iis.bsuir.by/api/v1/schedule" +
                "?studentGroup=" + query;

        RestTemplate restTemplate = new RestTemplate();

        ApiResponse response = restTemplate.getForObject(apiUrl, ApiResponse.class);

        if (response != null)
        {
            Schedule scheduleEntity = SCHEDULE_REPOSITORY.findByGroupName(response.getStudentGroupDto().getName());

            if(scheduleEntity == null) {
                scheduleEntity = new Schedule();
                scheduleEntity.setCourse(response.getStudentGroupDto().getCourse());
                scheduleEntity.setGroupName(response.getStudentGroupDto().getName());
                scheduleEntity.setFacultyId(response.getStudentGroupDto().getFacultyId());
                scheduleEntity.setFacultyAbbrev(response.getStudentGroupDto().getFacultyAbbrev());

                SCHEDULE_REPOSITORY.save(scheduleEntity);

                fillTables(response, scheduleEntity);
            }
        } else
            return null;

        return response;
    }

    public void fillTables(ApiResponse response, Schedule scheduleEntity) {
        for (List<org.javalabs.lab1.model.scheduledto.Schedule> scheduleList : response.getSchedules().values()) {
            for (org.javalabs.lab1.model.scheduledto.Schedule schedule : scheduleList) {
                for (String auditory : schedule.getAuditories()) {
                    Auditory auditoryEntity = new Auditory();
                    auditoryEntity.setAuditoryNumber(auditory);
                    auditoryEntity.setGroupName(response.getStudentGroupDto().getName());
                    auditoryEntity.setStartTime(schedule.getStartLessonTime());
                    auditoryEntity.setDate(schedule.getStartLessonDate());
                    auditoryEntity.setSchedule(scheduleEntity);

                    LessonType existingLessonType = LESSON_TYPE_REPOSITORY.findByType(
                            schedule.getLessonTypeAbbrev());
                    if (existingLessonType == null) {
                        LessonType lessonType = new LessonType();
                        lessonType.setType(schedule.getLessonTypeAbbrev());
                        LESSON_TYPE_REPOSITORY.save(lessonType);
                        auditoryEntity.getLessonTypes().add(lessonType);
                    } else {
                        auditoryEntity.getLessonTypes().add(existingLessonType);
                    }

                    AUDITORY_REPOSITORY.save(auditoryEntity);
                }
            }
        }
    }
    public Schedule createSchedule(Schedule scheduleEntity) {
        if (scheduleEntity == null) {
            throw new IllegalArgumentException("error");
        }

        return SCHEDULE_REPOSITORY.save(scheduleEntity);
    }

    public Schedule updateSchedule(int id, Schedule scheduleEntity) {
        if (scheduleEntity == null) {
            throw new IllegalArgumentException("error");
        }

        Optional<Schedule> existingEntityOptional = SCHEDULE_REPOSITORY.findById(id);
        if (existingEntityOptional.isEmpty()) {
            throw new IllegalArgumentException("id=" + id + " not found");
        }

        Schedule existingEntity = existingEntityOptional.get();

        existingEntity.setCourse(scheduleEntity.getCourse());
        existingEntity.setGroupName(scheduleEntity.getGroupName());
        existingEntity.setFacultyId(scheduleEntity.getFacultyId());
        existingEntity.setFacultyAbbrev(scheduleEntity.getFacultyAbbrev());
        return SCHEDULE_REPOSITORY.save(existingEntity);
    }

    public void deleteSchedule(int id) {
        SCHEDULE_REPOSITORY.deleteById(id);
    }

}