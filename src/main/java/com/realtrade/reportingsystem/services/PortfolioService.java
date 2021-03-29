package com.realtrade.reportingsystem.services;

import com.realtrade.reportingsystem.models.Client;
import com.realtrade.reportingsystem.models.ClientOrder;
import com.realtrade.reportingsystem.models.Portfolio;
import com.realtrade.reportingsystem.repository.PortfolioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortfolioService {
    private PortfolioDao portfolioDao;

    @Autowired
    public PortfolioService(PortfolioDao portfolioDao) {
        this.portfolioDao = portfolioDao;
    }

    public Portfolio createPortfolio(Portfolio portfolio) {
        Optional<Portfolio> portfolioOptional = portfolioDao.findPortfolioByPortfolioName(portfolio.getPortfolioName());

        if(portfolioOptional.isPresent()) {
            throw new IllegalStateException("Porfolio already exists");
        } else {
            Portfolio newPortfolio = new Portfolio();
            newPortfolio.setPortfolioName(portfolio.getPortfolioName());
            newPortfolio.setStatus(portfolio.getStatus());
            return portfolioDao.save(newPortfolio);
        }
    }

    public Optional<Portfolio> getPortfolioById(int portfolioId) {
        return portfolioDao.findById(portfolioId);
    }

    public List<Portfolio> getPortfoliosByClientId(int clientId) {
        return portfolioDao.findPortfoliosByClientId(clientId);
    }

    public void getProfitSummary(Portfolio porfolio) {

    }

    public List<Portfolio> getAllPortfolios() {
        return portfolioDao.findAll();
    }

    public List<Portfolio> getAllActivePortfolios() {
        return portfolioDao.getAllActivePortfolios();
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

    public boolean closePortfolio(int portfolioId) {
        Optional<Portfolio> porfolio = getPortfolioById(portfolioId);
        if(porfolio.isPresent()) {
            porfolio.get().setStatus(0);
            portfolioDao.save(porfolio.get());
            return true;
        } else return false;
    }

    public Optional<Portfolio> updatePortfolio(int portfolioId, Portfolio updatePortfolio) {
        Optional<Portfolio> portfolioOptional = getPortfolioById(portfolioId);
        if(portfolioOptional.isPresent()) {
            portfolioOptional.get().setPortfolioName(updatePortfolio.getPortfolioName());
            portfolioDao.save(portfolioOptional.get());
        }
        return portfolioOptional;
    }

    public Optional<Portfolio> addOrderToPortofolio(int portfolioId, ClientOrder order) {

        Optional<Portfolio> portfolioOptional = getPortfolioById(portfolioId);
        if(portfolioOptional.isPresent()) {
            List<ClientOrder> previousOrders = portfolioOptional.get().getOrders();
            previousOrders.add(order);
            portfolioOptional.get().setOrders(previousOrders);
            portfolioDao.save(portfolioOptional.get());
        }
        return portfolioOptional;
    }

    public Optional<Portfolio> addPortofolioClient(int portfolioId, Client client) {

        Optional<Portfolio> portfolioOptional = getPortfolioById(portfolioId);
        if(portfolioOptional.isPresent()) {
            portfolioOptional.get().setClient(client);
            portfolioDao.save(portfolioOptional.get());
        }
        return portfolioOptional;
    }
}
