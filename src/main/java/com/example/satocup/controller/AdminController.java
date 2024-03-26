package com.example.satocup.controller;

import com.example.satocup.model.dto.AdminDTO;
import com.example.satocup.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        List<AdminDTO> admins = adminService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable("id") Long adminId) {
        AdminDTO adminDTO = adminService.getAdminById(adminId);
        return new ResponseEntity<>(adminDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AdminDTO> createAdmin(@Valid @RequestBody AdminDTO adminDTO) {
        AdminDTO createdAdminDTO = adminService.createAdmin(adminDTO);
        return new ResponseEntity<>(createdAdminDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable("id") Long adminId, @Valid @RequestBody AdminDTO adminDTO) {
        AdminDTO updatedAdminDTO = adminService.updateAdmin(adminId, adminDTO);
        return new ResponseEntity<>(updatedAdminDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable("id") Long adminId) {
        adminService.deleteAdmin(adminId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
