package org.javalabs.lab1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import  org.javalabs.lab1.entity.ScheduleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {
    ScheduleEntity findByGroupName(String  group);
}