package org.javalabs.lab1.dao;

import org.javalabs.lab1.entity.LessonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonTypeRepository extends JpaRepository<LessonType, Integer> {
    LessonType findByType(String abbrev);
}
