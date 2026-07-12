package com.example.distributed_system.repositories;

import com.example.distributed_system.entities.Staff;
import com.example.distributed_system.entities.enums.ContractType;
import com.example.distributed_system.entities.enums.StaffRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface StaffRepository extends JpaRepository<Staff,UUID>{
    Optional<Staff> findByEmail(String email);
    List<Staff> findByContractType(ContractType contractType);
    List<Staff> findByRole(StaffRole role);
    boolean existsByEmail(String email);
}
