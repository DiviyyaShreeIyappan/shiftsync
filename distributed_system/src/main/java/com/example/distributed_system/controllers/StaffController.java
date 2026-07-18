package com.example.distributed_system.controllers;

import com.example.distributed_system.entities.Staff;
import com.example.distributed_system.entities.enums.ContractType;
import com.example.distributed_system.entities.enums.StaffRole;
import com.example.distributed_system.services.StaffService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;

    @GetMapping
    public List<Staff> getAllStaff(){
         return staffService.getAllStaff();
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable UUID id){
        return staffService.getStaffById(id);
    }

    @GetMapping("/email/{email}")
    public Staff getStaffByEmail(@PathVariable String email){
        return staffService.getStaffByEmail(email);
    }
    @GetMapping("/fixed")
    public List<Staff> getFixedStaff(){
        return staffService.getFixedStaff();
    }
    @GetMapping("/flexible")
    public List<Staff> getFlexibleStaff() {
        return staffService.getFlexibleStaff();
    }
    @GetMapping("/role/{role}")
    public List<Staff> getStaffByRole(@PathVariable StaffRole role){
        return staffService.getStaffByRole(role);
    }
    @GetMapping("/contract/{contractType}")
    public List<Staff> getStaffByContractType(@PathVariable ContractType contractType) {
        return staffService.getStaffByContractType(contractType);
    }
    @PostMapping
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff){
        return ResponseEntity.status(201).body(staffService.createStaff(staff));
    }


    @PutMapping("/{id}")
    public Staff updateStaff(@PathVariable UUID id, @RequestBody Staff staff) {
        return staffService.updateStaff(id,staff);
    }
    @PatchMapping("/{id}")
    public Staff patchStaff(@PathVariable UUID id, @RequestBody Staff staff) {
        return staffService.patchStaff(id, staff);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable UUID id) {
        staffService.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }
}
