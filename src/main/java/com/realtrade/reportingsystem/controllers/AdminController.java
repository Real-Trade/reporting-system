package com.realtrade.reportingsystem.controllers;

import com.realtrade.reportingsystem.models.Admin;
import com.realtrade.reportingsystem.services.AdminService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
@CrossOrigin("*")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping(path = "/active")
    public List<Admin> getAllActiveAdmin() {
        return adminService.getAllActiveAdmins();
    }

    @GetMapping(path = "/{adminId}")
    public Admin getAdminById(@PathVariable(name = "adminId") int adminId) {
        return adminService.getAdminById(adminId).orElseThrow();
    }

    @PutMapping(path = "/{adminId}/update")
    public Admin updateAdmin(@PathVariable(name = "adminId") int adminId, @RequestBody Admin admin) {
        return adminService.updateAdmin(adminId, admin).orElseThrow();
    }

    @DeleteMapping(path = "/{adminId}/delete")
    public boolean deleteAdmin(@PathVariable(name = "adminId") int adminId) {
        return adminService.deleteAdmin(adminId);
    }


}
