package com.realtrade.reportingsystem.repository;

import com.realtrade.reportingsystem.models.ClientOrder;
import com.realtrade.reportingsystem.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioDao extends JpaRepository<Portfolio, Integer> {

    @Query("select p from Portfolio p where p.client.clientId = ?1")
    List<Portfolio> findPortfoliosByClientId(int clientId);

    @Query("select o from Portfolio p join p.orders o order by o.updatedAt desc")
    List<ClientOrder> getLastActivityDate();

    @Query("select o from Portfolio p join p.orders o")
    List<ClientOrder> getAllOrders();

    @Query("select o from Portfolio p join p.orders o where o.product = ?1")
    List<ClientOrder> getOrdersByProduct(String product);

    @Query("select o from Portfolio  p join p.orders o where o.status = ?1")
    List<ClientOrder> getOrdersByStatus(String status);

    Optional<Portfolio> findPortfolioByPortfolioName(String portfolioName);

    @Query("select p from Portfolio p where p.status = 1")
    List<Portfolio> getAllActivePortfolios();

}
