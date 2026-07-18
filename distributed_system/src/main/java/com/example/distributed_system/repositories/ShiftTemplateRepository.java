package com.example.distributed_system.repositories;

import com.example.distributed_system.entities.ShiftTemplate;
import com.example.distributed_system.entities.enums.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShiftTemplateRepository extends JpaRepository<ShiftTemplate, UUID> {
    List<ShiftTemplate> findByDepartment(Department department);
    List<ShiftTemplate> findByActive(Boolean active);
    List<ShiftTemplate> findByDepartmentAndActive(Department department, Boolean active);
}