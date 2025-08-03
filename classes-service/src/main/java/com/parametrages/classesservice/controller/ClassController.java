package com.parametrages.classesservice.controller;

import com.parametrages.classes.entity.ClassEntity;
import com.parametrages.classes.repository.ClassRepository;
import com.parametrages.classes.entity.Sector;
import com.parametrages.classes.repository.SectorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private SectorClient sectorClient;

    @GetMapping
    public List<ClassEntity> getAll() {
        return classRepository.findAll();
    }

    @GetMapping("/by-sector/{sectorId}")
    public List<ClassEntity> getBySector(@PathVariable Long sectorId) {
        return classRepository.findBySectorId(sectorId);
    }

    @PostMapping
    public ClassEntity create(@RequestBody ClassEntity classEntity) {
        Sector sector = sectorClient.getSectorById(classEntity.getSectorId());
        if (sector == null) throw new RuntimeException("Sector not found");
        return classRepository.save(classEntity);
    }

    @PutMapping("/{id}")
    public ClassEntity update(@PathVariable Long id, @RequestBody ClassEntity updatedClass) {
        return classRepository.findById(id).map(existing -> {
            existing.setClassName(updatedClass.getClassName());
            existing.setDescription(updatedClass.getDescription());
            existing.setSectorId(updatedClass.getSectorId());
            return classRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Class not found"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!classRepository.existsById(id)) {
            throw new RuntimeException("Class not found");
        }
        classRepository.deleteById(id);
    }
}
