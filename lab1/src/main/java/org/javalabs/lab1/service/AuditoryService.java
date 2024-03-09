package org.javalabs.lab1.service;

import org.javalabs.lab1.dao.AuditoryRepository;
import org.javalabs.lab1.dao.ScheduleRepository;
import org.javalabs.lab1.entity.Auditory;
import org.javalabs.lab1.entity.Schedule;
import org.javalabs.lab1.model.auditorydto.AuditoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditoryService {
    private final AuditoryRepository auditoryRepository;
    private final ScheduleRepository scheduleRepository;
    private static final String STATUS_CODE_ERROR = "error";
    public AuditoryService(AuditoryRepository auditoryRepository, ScheduleRepository scheduleRepository) {
        this.auditoryRepository = auditoryRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public Auditory getAuditoryById(int id) {
        Optional<Auditory> auditoryOptional = auditoryRepository.findById(id);

        return auditoryOptional.orElse(null);
    }

    public Auditory createAuditory(Auditory auditoryEntity, String group) {
        if (auditoryEntity == null) {
            throw new IllegalArgumentException(STATUS_CODE_ERROR);
        }
        Schedule schedule = scheduleRepository.findByGroupName(group);
        if (schedule != null) {
            auditoryEntity.setSchedule(schedule);

            return auditoryRepository.save(auditoryEntity);
        } else {
            throw new IllegalArgumentException(STATUS_CODE_ERROR);
        }
    }

    public List<AuditoryDto> getAuditoriesByDateAndScheduleId(String date, String groupName) {
        Schedule schedule = scheduleRepository.findByGroupName(groupName);
        return auditoryRepository.findByDateAndScheduleId(date, schedule.getId());
    }

    public Auditory updateAuditory(int id, Auditory auditoryEntity) {
        if (auditoryEntity == null) {
            throw new IllegalArgumentException(STATUS_CODE_ERROR);
        }

        Optional<Auditory> existingEntityOptional = auditoryRepository.findById(id);
        if (existingEntityOptional.isEmpty()) {
            throw new IllegalArgumentException("id=" + id + " not found");
        }

        Auditory existingEntity = existingEntityOptional.get();

        existingEntity.setAuditoryNumber(auditoryEntity.getAuditoryNumber());
        existingEntity.setGroupName(auditoryEntity.getGroupName());
        existingEntity.setStartTime(auditoryEntity.getStartTime());

        return auditoryRepository.save(existingEntity);
    }

    public void deleteAuditory(int id) {
        auditoryRepository.deleteById(id);
    }

}
