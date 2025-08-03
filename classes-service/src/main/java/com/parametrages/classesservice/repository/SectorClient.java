package com.parametrages.classes.repository;

import com.parametrages.classes.entity.Sector;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "sectors-service")
public interface SectorClient {
    @GetMapping("/sectors/{id}")
    Sector getSectorById(@PathVariable Long id);
}
