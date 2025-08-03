package com.parametrages.classes.repository;

import com.parametrages.classes.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
    List<ClassEntity> findBySectorId(Long sectorId);
}
