package com.realtrade.reportingsystem.repository;

import com.realtrade.reportingsystem.models.Client;
import com.realtrade.reportingsystem.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDao extends JpaRepository<Client, Integer> {

    @Query("select c from Client c where c.status = 1")
    List<Client> getAllActiveClients();

}
