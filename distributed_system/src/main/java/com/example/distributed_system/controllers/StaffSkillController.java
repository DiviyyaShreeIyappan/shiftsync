package com.example.distributed_system.controllers;

import com.example.distributed_system.entities.StaffSkill;
import com.example.distributed_system.entities.enums.Department;
import com.example.distributed_system.repositories.StaffRepository;
import com.example.distributed_system.repositories.StaffSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class StaffSkillController {

    private final StaffSkillRepository staffSkillRepository;
    private final StaffRepository staffRepository;

    @PostMapping
    public ResponseEntity<StaffSkill> addSkill(
            @RequestParam UUID staffId,
            @RequestParam Department department) {

        StaffSkill skill = StaffSkill.builder()
                .staff(staffRepository.findById(staffId).orElseThrow())
                .department(department)
                .build();

        return ResponseEntity.status(201).body(staffSkillRepository.save(skill));
    }
}