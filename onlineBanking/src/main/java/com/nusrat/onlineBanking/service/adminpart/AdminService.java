package com.nusrat.onlineBanking.service.adminPart;

import com.nusrat.onlineBanking.entities.adminPart.Admin;
import com.nusrat.onlineBanking.repository.adminpart.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Admin not found"));
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long id, Admin adminDetails) {
        Admin admin = getAdminById(id);
        admin.setName(adminDetails.getName());
        admin.setEmail(adminDetails.getEmail());
        return adminRepository.save(admin);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

}
