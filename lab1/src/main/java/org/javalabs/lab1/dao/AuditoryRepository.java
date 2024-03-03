package org.javalabs.lab1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.javalabs.lab1.entity.AuditoryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoryRepository extends JpaRepository<AuditoryEntity, Integer> {
}
