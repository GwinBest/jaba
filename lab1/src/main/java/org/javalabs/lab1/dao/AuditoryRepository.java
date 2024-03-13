package org.javalabs.lab1.dao;

import java.util.List;
import org.javalabs.lab1.entity.Auditory;
import org.javalabs.lab1.model.auditorydto.AuditoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AuditoryRepository extends JpaRepository<Auditory, Integer> {
    @Query(value = "SELECT new org.javalabs.lab1.model."
            + "auditorydto.AuditoryDto(a.auditoryNumber, a.startTime, s.groupName) "
            + "FROM Auditory a "
            + "JOIN Schedule s ON a.schedule.id = s.id "
            + "WHERE a.date = :date "
            + "AND s.id = :id")
    List<AuditoryDto> findByDateAndScheduleId(@Param("date") String date, @Param("id") int id);

}
