package com.realtrade.reportingsystem.repository;

import com.realtrade.reportingsystem.models.Client;
import com.realtrade.reportingsystem.models.ClientOrder;
import com.realtrade.reportingsystem.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PortfolioDao extends JpaRepository<Portfolio, Integer> {

    List<Portfolio> findAllByClient(Client client);

    @Query("select o from Portfolio p join p.orders o order by o.updatedAt desc")
    List<ClientOrder> getLastActivityDate();

    @Query("select o from Portfolio p join p.orders o")
    List<ClientOrder> getAllOrders();

    @Query("select o from Portfolio p join p.orders o where o.product = ?1")
    List<ClientOrder> getOrdersByProduct(String product);

    @Query("select o from Portfolio  p join p.orders o where o.status = ?1")
    List<ClientOrder> getOrdersByStatus(String status);

}
