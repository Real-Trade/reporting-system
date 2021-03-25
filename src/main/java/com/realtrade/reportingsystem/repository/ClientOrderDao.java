package com.realtrade.reportingsystem.repository;

import com.realtrade.reportingsystem.models.ClientOrder;
import com.realtrade.reportingsystem.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientOrderDao extends JpaRepository<ClientOrder, Integer> {

    List<ClientOrder> getClientOrdersByProduct(String product);

    List<ClientOrder> getClientOrdersByStatus(String status);

    List<ClientOrder> getClientOrdersBySide(String side);

    List<ClientOrder> findClientOrderByPortfolio(Portfolio portfolio);

    @Query("select o from ClientOrder o where o.portfolio.portfolioId = ?1")
    List<ClientOrder> findClientOrdersByPortfolioId(int portfolioId);

    @Query("select o from ClientOrder o where o.client.clientId = ?1")
    List<ClientOrder> findClientOrdersByClientId(int clientId);
}
