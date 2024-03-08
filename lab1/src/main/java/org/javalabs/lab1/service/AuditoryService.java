package org.javalabs.lab1.service;

import org.javalabs.lab1.dao.AuditoryRepository;
import org.javalabs.lab1.dao.ScheduleRepository;
import org.javalabs.lab1.entity.Auditory;
import org.javalabs.lab1.entity.Schedule;
import org.javalabs.lab1.model.auditorydto.AuditoryDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class AuditoryService {
    private final AuditoryRepository AUDITORY_REPOSITORY;
    private final ScheduleRepository SCHEDULE_REPOSITORY;
    private static final String STATUS_CODE_ERROR = "error";
    public AuditoryService(AuditoryRepository auditoryRepository, ScheduleRepository scheduleRepository) {
        this.AUDITORY_REPOSITORY = auditoryRepository;
        this.SCHEDULE_REPOSITORY = scheduleRepository;
    }

    public Auditory getAuditoryById(int id) {
        Optional<Auditory> auditoryOptional = AUDITORY_REPOSITORY.findById(id);

        return auditoryOptional.orElse(null);
    }

    public Auditory createAuditory(Auditory auditoryEntity, String group) {
        if (auditoryEntity == null) {
            throw new IllegalArgumentException(STATUS_CODE_ERROR);
        }
        Schedule schedule = SCHEDULE_REPOSITORY.findByGroupName(group);
        if(schedule != null) {
            auditoryEntity.setSchedule(schedule);

            return AUDITORY_REPOSITORY.save(auditoryEntity);
        } else {
            throw new IllegalArgumentException(STATUS_CODE_ERROR);
        }
    }

    public List<AuditoryDto> getAuditoriesByDateAndScheduleId(String date, String groupName) {
        Schedule schedule = SCHEDULE_REPOSITORY.findByGroupName(groupName);
        return AUDITORY_REPOSITORY.findByDateAndScheduleId(date, schedule.getId());
    }

    public Auditory updateAuditory(int id, Auditory auditoryEntity) {
        if (auditoryEntity == null) {
            throw new IllegalArgumentException(STATUS_CODE_ERROR);
        }

        Optional<Auditory> existingEntityOptional = AUDITORY_REPOSITORY.findById(id);
        if (existingEntityOptional.isEmpty()) {
            throw new IllegalArgumentException("id=" + id + " not found");
        }

        Auditory existingEntity = existingEntityOptional.get();

        existingEntity.setAuditoryNumber(auditoryEntity.getAuditoryNumber());
        existingEntity.setGroupName(auditoryEntity.getGroupName());
        existingEntity.setStartTime(auditoryEntity.getStartTime());

        return AUDITORY_REPOSITORY.save(existingEntity);
    }

    public void deleteAuditory(int id) {
        AUDITORY_REPOSITORY.deleteById(id);
    }

}
