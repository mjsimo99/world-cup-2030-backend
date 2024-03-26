package com.example.satocup.service.impl;

import com.example.satocup.model.dto.AdminDTO;
import com.example.satocup.model.entity.Admin;
import com.example.satocup.repository.AdminRepository;
import com.example.satocup.service.AdminService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, ModelMapper modelMapper) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AdminDTO> getAllAdmins() {
        try {
            List<Admin> admins = adminRepository.findAll();
            return admins.stream()
                    .map(admin -> modelMapper.map(admin, AdminDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all admins: " + e.getMessage());
        }
    }

    @Override
    public AdminDTO getAdminById(Long adminId) {
        try {
            Admin admin = adminRepository.findById(adminId)
                    .orElseThrow(() -> new NotFoundException("Admin not found with ID: " + adminId));
            return modelMapper.map(admin, AdminDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch admin with ID " + adminId + ": " + e.getMessage());
        }
    }

    @Override
    public AdminDTO createAdmin(AdminDTO adminDTO) {
        try {
            Admin admin = modelMapper.map(adminDTO, Admin.class);
            admin = adminRepository.save(admin);
            return modelMapper.map(admin, AdminDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create admin: " + e.getMessage());
        }
    }

    @Override
    public AdminDTO updateAdmin(Long adminId, AdminDTO adminDTO) {
        try {
            Admin existingAdmin = adminRepository.findById(adminId)
                    .orElseThrow(() -> new NotFoundException("Admin not found with ID: " + adminId));
            modelMapper.map(adminDTO, existingAdmin);
            existingAdmin.setAdminId(adminId);
            existingAdmin = adminRepository.save(existingAdmin);
            return modelMapper.map(existingAdmin, AdminDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update admin with ID " + adminId + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteAdmin(Long adminId) {
        try {
            if (!adminRepository.existsById(adminId)) {
                throw new NotFoundException("Admin not found with ID: " + adminId);
            }
            adminRepository.deleteById(adminId);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete admin with ID " + adminId);
        }
    }
}
