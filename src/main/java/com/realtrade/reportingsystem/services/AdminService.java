package com.realtrade.reportingsystem.services;

import com.realtrade.reportingsystem.models.Admin;
import com.realtrade.reportingsystem.repository.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AdminService  {
    private AdminDao adminDao;

    @Autowired
    public AdminService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    //create admin
    public Admin createAdmin(Admin admin) {
        adminDao.save(admin);
        return admin;
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
    public Admin updateAdmin(Admin adminUpdate) {
        return adminDao.save(adminUpdate);
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
