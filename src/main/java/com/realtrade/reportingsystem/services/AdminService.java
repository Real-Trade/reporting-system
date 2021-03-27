package com.realtrade.reportingsystem.services;

import com.realtrade.reportingsystem.models.Admin;
import com.realtrade.reportingsystem.repository.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService  {
    private AdminDao adminDao;

    @Autowired
    public AdminService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    //create admin
    public Admin createAdmin(Admin admin) {
        Optional<Admin> adminOptional = adminDao.getAdminByEmail(admin.getEmail());
        if(adminOptional.isPresent()) {
            throw new IllegalStateException("Admin already exists");
        } else {
            Admin newAdmin = new Admin();
            newAdmin.setFirstName(admin.getFirstName());
            newAdmin.setLastName(admin.getLastName());
            newAdmin.setEmail(admin.getEmail());
            newAdmin.setPassword(admin.getPassword());
            newAdmin.setStatus(admin.getStatus());
            newAdmin.setCreatedAt(OffsetDateTime.now());
            newAdmin.setUpdatedAt(OffsetDateTime.now());
            return adminDao.save(newAdmin);
        }
    }

    public Optional<Admin> getAdminById(int adminId) {
        return adminDao.findById(adminId);
    }

    //get all admins
    public List<Admin> getAllAdmins() {
        return adminDao.findAll();
    }

    public List<Admin> getAllActiveAdmins() {
        return adminDao.getAllActiveAdmins();
    }

    //update admin
    public Optional<Admin> updateAdmin(int adminId, Admin adminUpdate) {
        Optional<Admin> adminOptional = getAdminById(adminId);
        if(adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            if(!adminUpdate.getFirstName().isEmpty()) {
                admin.setFirstName(adminUpdate.getFirstName());
            }
            if(!adminUpdate.getLastName().isEmpty()) {
                admin.setLastName(adminUpdate.getLastName());
            }
            if(!adminUpdate.getEmail().isEmpty()) {
                admin.setEmail(adminUpdate.getEmail());
            }
            admin.setUpdatedAt(OffsetDateTime.now());
            adminDao.save(admin);
        }
        return adminOptional;
    }

    //delete admin
    public boolean deleteAdmin(int adminId) {
        Optional<Admin> admin = adminDao.findById(adminId);
        if(admin.isPresent()) {
            admin.get().setStatus(0);
            adminDao.save(admin.get());
            return true;
        }
        return false;
    }
}
