// AdminService.java
package com.example.satocup.service;

import com.example.satocup.model.dto.AdminDTO;

import java.util.List;

public interface AdminService {
    List<AdminDTO> getAllAdmins();
    AdminDTO getAdminById(Long adminId);
    AdminDTO createAdmin(AdminDTO adminDTO);
    AdminDTO updateAdmin(Long adminId, AdminDTO adminDTO);
    void deleteAdmin(Long adminId);


}
