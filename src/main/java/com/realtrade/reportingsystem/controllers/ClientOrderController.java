package com.realtrade.reportingsystem.controllers;

import com.realtrade.reportingsystem.models.ClientOrder;
import com.realtrade.reportingsystem.services.ClientOrderService;
import com.realtrade.reportingsystem.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class ClientOrderController {
    private ClientOrderService orderService;

    @Autowired
    public ClientOrderController(ClientOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<ClientOrder> getAllOrders() {
        return orderService.getAllClientOrders();
    }

    @GetMapping(path = "/{orderId}")
    public ClientOrder getOrderById(@PathVariable(name = "orderId")int orderId) {
        return orderService.getOrderById(orderId).orElseThrow();
    }

    @GetMapping(path = "/portfolio/{portfolioId}")
    public List<ClientOrder> getOrderByPortfolioId(@PathVariable(name = "portfolioId") int portfolioId) {
        return orderService.getOrderByPortfolioId(portfolioId);
    }

    @GetMapping(path = "/client/{clientId}")
    public List<ClientOrder> getOrderByClientId(@PathVariable(name = "clientId") int clientId) {
        return orderService.getOrderByClientId(clientId);
    }

    @GetMapping(path = "/status/{status}")
    public List<ClientOrder> getOrderByStatus(@PathVariable(name = "status")String status) {
        return orderService.getOrderByStatus(status);
    }

    @GetMapping(path = "/product/{product}")
    public List<ClientOrder> getOrderByProduct(@PathVariable(name = "product")String product) {
        return orderService.getOrderByProduct(product);
    }

    @GetMapping(path = "/side/{side}")
    public List<ClientOrder> getOrderBySide(@PathVariable(name = "side")String side) {
        return orderService.getOrderBySide(side);
    }

//    @DeleteMapping(path = "/{orderId}/delete")
//    public boolean deleteOrder(@PathVariable(name = "orderId")int orderId) {
//        return orderService.deleteClientOrder(orderId);
//    }
}
