package org.javalabs.lab1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.javalabs.lab1.entity.Schedule;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Schedule findByGroupName(String group);
}
