package com.example.distributed_system.services;


import com.example.distributed_system.entities.Staff;
import com.example.distributed_system.entities.enums.ContractType;
import com.example.distributed_system.entities.enums.StaffRole;
import com.example.distributed_system.repositories.StaffRepository;
import com.example.distributed_system.repositories.StaffSkillRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class StaffService {
    private final StaffRepository staffRepository;
    private final StaffSkillRepository staffSkillRepository;

    @Transactional(readOnly = true)
    public List<Staff> getAllStaff(){
        return staffRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Staff getStaffById(UUID id){
        return staffRepository.findById(id).orElseThrow(() -> new RuntimeException("Staff not found with id: "+id));
    }

    @Transactional(readOnly=true)
    public Staff getStaffByEmail(String email){
        return staffRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Staff not found with email" + email));
    }

    @Transactional(readOnly = true)
    public List<Staff> getStaffByContractType(ContractType contractType){
        return staffRepository.findByContractType(contractType);
    }

    @Transactional(readOnly = true)
    public List<Staff> getStaffByRole(StaffRole role){
        return staffRepository.findByRole(role);
    }
    @Transactional(readOnly = true)
    public List<Staff> getFixedStaff() {
        return staffRepository.findByContractType(ContractType.FIXED);
    }

    @Transactional(readOnly = true)
    public List<Staff> getFlexibleStaff() {
        return staffRepository.findByContractType(ContractType.FLEXIBLE);
    }

    public Staff createStaff(Staff staff){
        if(staffRepository.existsByEmail(staff.getEmail())){
            throw new RuntimeException("Staff with email"+ staff.getEmail() +"already exists");
        }
        return staffRepository.save(staff);
    }

    public Staff updateStaff(UUID id,Staff updatedStaff){
        Staff existing = getStaffById(id);
        existing.setName(updatedStaff.getName());
        existing.setPhone(updatedStaff.getPhone());
        existing.setContractType(updatedStaff.getContractType());
        existing.setContractedHours(updatedStaff.getContractedHours());
        existing.setRole(updatedStaff.getRole());
        return staffRepository.save(existing);
    }

    public Staff patchStaff(UUID id, Staff updatedStaff) {
        Staff existing = getStaffById(id);

        // only update if the incoming value is not null
        if (updatedStaff.getName() != null) {
            existing.setName(updatedStaff.getName());
        }
        if (updatedStaff.getPhone() != null) {
            existing.setPhone(updatedStaff.getPhone());
        }
        if (updatedStaff.getContractType() != null) {
            existing.setContractType(updatedStaff.getContractType());
        }
        if (updatedStaff.getContractedHours() != null) {
            existing.setContractedHours(updatedStaff.getContractedHours());
        }
        if (updatedStaff.getRole() != null) {
            existing.setRole(updatedStaff.getRole());
        }

        return staffRepository.save(existing);
    }
    public void deleteStaff(UUID id) {
        if (!staffRepository.existsById(id)) {
            throw new RuntimeException("Staff not found with id: " + id);
        }
        staffRepository.deleteById(id);
    }

}
