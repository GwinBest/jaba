package org.javalabs.lab1.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;
import org.javalabs.lab1.dao.AuditoryRepository;
import org.javalabs.lab1.dao.ScheduleRepository;
import org.javalabs.lab1.entity.Auditory;
import org.javalabs.lab1.entity.Schedule;
import org.javalabs.lab1.model.auditorydto.AuditoryDto;
import org.springframework.stereotype.Service;

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

    public void createAuditoriesBulk(List<Auditory> auditories, String group) {
        if (auditories == null || auditories.isEmpty()) {
            throw new IllegalArgumentException("No auditories provided");
        }
        if (scheduleRepository.findByGroupName(group) == null) {
            throw new IllegalArgumentException("No such auditory");
        }

        List<String> errors = auditories.stream()
                .map(auditory -> {
                    try {
                        createAuditory(auditory, group);
                        return null;
                    } catch (Exception e) {
                        return "Error creating auditory: " + e.getMessage();
                    }
                })
                .filter(Objects::nonNull)
                .toList();

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Errors occurred during bulk creation:\n"
                    + String.join("\n", errors));
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
        Optional<Auditory> auditoryOptional = auditoryRepository.findById(id);
        if (auditoryOptional.isPresent()) {
            auditoryRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Auditory with id " + id + " not found");
        }
    }

}
