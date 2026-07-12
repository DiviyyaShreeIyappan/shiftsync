package com.example.distributed_system.repositories;

import com.example.distributed_system.entities.StaffSkill;
import com.example.distributed_system.entities.enums.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface StaffSkillRepository extends JpaRepository<StaffSkill, UUID> {

    List<StaffSkill> findByStaffId(UUID staffId);
    List<StaffSkill> findByDepartment(Department department);
    List<StaffSkill> findByStaffIdAndDepartment(UUID staffId, Department department);
    boolean existsByStaffIdAndDepartment(UUID staffId, Department department);
}