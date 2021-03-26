package com.realtrade.reportingsystem.repository;

import com.realtrade.reportingsystem.models.Account;
import com.realtrade.reportingsystem.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {
    Account getAccountByClient(Client client);

    @Query("select a from Account a where a.client.clientId = ?1")
    Optional<Account> getAccountByClientId(int clientId);

}
