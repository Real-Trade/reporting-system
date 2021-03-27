package com.realtrade.reportingsystem.services;

import com.realtrade.reportingsystem.models.ClientOrder;
import com.realtrade.reportingsystem.repository.ClientDao;
import com.realtrade.reportingsystem.repository.ClientOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientOrderService {
    private ClientOrderDao clientOrderDao;

    @Autowired
    public ClientOrderService(ClientOrderDao clientOrderDao) {
        this.clientOrderDao = clientOrderDao;
    }

    public Optional<ClientOrder> getOrderById(int clientOrderId) {
        return clientOrderDao.findById(clientOrderId);
    }

    public List<ClientOrder> getOrderByPortfolioId(int portfolioId) {
        return clientOrderDao.findClientOrdersByPortfolioId(portfolioId);
    }

    public List<ClientOrder> getOrderByClientId(int clientId) {
        return clientOrderDao.findClientOrdersByClientId(clientId);
    }

    public List<ClientOrder> getAllClientOrders() {
        return clientOrderDao.findAll();
    }

    public List<ClientOrder> getOrderByStatus(String status) {
        return clientOrderDao.getClientOrdersByStatus(status);
    }

    public List<ClientOrder> getOrderByProduct(String product) {
        return clientOrderDao.getClientOrdersByProduct(product);
    }

    public List<ClientOrder> getOrderBySide(String side) {
        return clientOrderDao.getClientOrdersBySide(side);
    }

//    public boolean deleteClientOrder(int clientOrderId) {
//        Optional<ClientOrder> clientOrder = getOrderById(clientOrderId);
//        if(clientOrder.isPresent()) {
//            clientOrder.get().setStatus("CANCELLED");
//            clientOrderDao.save(clientOrder.get());
//            return true;
//        }
//        return false;
//    }

}
