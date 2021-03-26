package com.realtrade.reportingsystem.services;

import com.realtrade.reportingsystem.models.Client;
import com.realtrade.reportingsystem.models.ClientOrder;
import com.realtrade.reportingsystem.models.Portfolio;
import com.realtrade.reportingsystem.repository.PortfolioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class PortfolioService {
    private PortfolioDao portfolioDao;

    @Autowired
    public PortfolioService(PortfolioDao portfolioDao) {
        this.portfolioDao = portfolioDao;
    }

    public Portfolio createPortfolio(Portfolio portfolio) {
        return portfolioDao.save(portfolio);
    }

    public Portfolio getPortfolioById(int portfolioId) {
        return portfolioDao.findById(portfolioId).orElseThrow(() ->
            new IllegalStateException("portfolio does not exist"));
    }

    public List<Portfolio> getPortfoliosByClient(Client client) {
        return portfolioDao.findAllByClient(client);
    }

    public void getProfitSummary(Portfolio porfolio) {

    }

    public List<ClientOrder> getLastActivityDate() {
        return portfolioDao.getLastActivityDate();
    }

    public List<ClientOrder> getAllOrders() {
        return portfolioDao.getAllOrders();
    }

    public List<ClientOrder> getOrdersByProduct(String product) {
        return portfolioDao.getOrdersByProduct(product);
    }

    public List<ClientOrder> getOrderByStatus(String status) {
        return portfolioDao.getOrdersByStatus(status);
    }

    @Transactional
    public boolean closePortfolio(int portfolioId) {
        Portfolio porfolio = getPortfolioById(portfolioId);
        porfolio.setStatus(0);
        portfolioDao.save(porfolio);
        return true;
    }

    @Transactional
    public Portfolio updatePortfolio(int portfolioId, String porfolioName) {
        Portfolio porfolio = getPortfolioById(portfolioId);
        if(!porfolioName.isEmpty()) {
            porfolio.setPortfolioName(porfolioName);
        }
        return portfolioDao.save(porfolio);
    }

    @Transactional
    public Portfolio addOrderToPortofolio(int portfolioId, ClientOrder order) {
        Portfolio porfolio = getPortfolioById(portfolioId);
        List<ClientOrder> previousOrders = porfolio.getOrders();
        previousOrders.add(order);
        porfolio.setOrders(previousOrders);
        portfolioDao.save(porfolio);
        return porfolio;
    }

}
