package com.realtrade.reportingsystem.repository;

import com.realtrade.reportingsystem.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {

    @Query("select a from Admin a where a.status = 1")
    List<Admin> getAllActiveAdmins();
}
