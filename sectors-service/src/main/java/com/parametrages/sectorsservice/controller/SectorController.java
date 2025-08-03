package com.parametrages.sectorsservice.controller;

import com.parametrages.sectorsservice.repository.SectorRepository;
import com.parametrages.sectorsservice.entity.Sector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sectors")
public class SectorController {

    @Autowired
    private SectorRepository sectorRepository;

    @GetMapping
    public List<Sector> getAll() {
        return sectorRepository.findAll();
    }

    @PostMapping
    public Sector create(@RequestBody Sector sector) {
        return sectorRepository.save(sector);
    }

    @GetMapping("/{id}")
    public Sector getById(@PathVariable Long id) {
        return sectorRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Sector update(@PathVariable Long id, @RequestBody Sector updatedSector) {
        return sectorRepository.findById(id).map(sector -> {
            sector.setName(updatedSector.getName());
            return sectorRepository.save(sector);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sectorRepository.deleteById(id);
    }

}
