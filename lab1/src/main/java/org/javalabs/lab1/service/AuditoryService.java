package org.javalabs.lab1.service;

import org.javalabs.lab1.dao.AuditoryRepository;
import org.javalabs.lab1.dao.ScheduleRepository;
import org.javalabs.lab1.entity.AuditoryEntity;
import org.javalabs.lab1.entity.ScheduleEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuditoryService {
    private final AuditoryRepository auditoryRepository;
    private final ScheduleRepository scheduleRepository;

    public AuditoryService(AuditoryRepository auditoryRepository, ScheduleRepository scheduleRepository) {
        this.auditoryRepository = auditoryRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public AuditoryEntity getAuditoryById(int id) {
        Optional<AuditoryEntity> auditoryOptional = auditoryRepository.findById(id);

        if (auditoryOptional.isPresent()) {
            return auditoryOptional.get();
        } else {
            return null;
        }
    }

    public AuditoryEntity createAuditory(AuditoryEntity auditoryEntity, String group) {
        if (auditoryEntity == null) {
            throw new IllegalArgumentException("error");
        }
        ScheduleEntity schedule = scheduleRepository.findByGroupName(group);
        if(schedule != null) {
            auditoryEntity.setSchedule(schedule);

            return auditoryRepository.save(auditoryEntity);
        } else {
            throw new IllegalArgumentException("error");
        }
    }

    public AuditoryEntity updateAuditory(int id, AuditoryEntity auditoryEntity) {
        if (auditoryEntity == null) {
            throw new IllegalArgumentException("error");
        }

        Optional<AuditoryEntity> existingEntityOptional = auditoryRepository.findById(id);
        if (existingEntityOptional.isEmpty()) {
            throw new IllegalArgumentException("id=" + id + " not found");
        }

        AuditoryEntity existingEntity = existingEntityOptional.get();

        existingEntity.setAuditoryNumber(auditoryEntity.getAuditoryNumber());
        existingEntity.setGroupName(auditoryEntity.getGroupName());
        existingEntity.setStartTime(auditoryEntity.getStartTime());

        return auditoryRepository.save(existingEntity);
    }

    public void deleteAuditory(int id) {
        auditoryRepository.deleteById(id);
    }

}
