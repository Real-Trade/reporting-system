package com.realtrade.reportingsystem.controllers;

import com.realtrade.reportingsystem.models.Client;
import com.realtrade.reportingsystem.models.ClientOrder;
import com.realtrade.reportingsystem.models.Portfolio;
import com.realtrade.reportingsystem.services.PortfolioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/portfolio")
public class PortfolioController {
    private PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PostMapping
    public Portfolio createPortfolio(@RequestBody Portfolio portfolio) {
        return portfolioService.createPortfolio(portfolio);
    }

    @GetMapping
    public List<Portfolio> getAllPortfolios() {
        return portfolioService.getAllPortfolios();
    }

    @GetMapping(path = "/active")
    public List<Portfolio> getAllActivePortfolios() {
        return portfolioService.getAllActivePortfolios();
    }

    @GetMapping(path = "/{portfolioId}")
    public Portfolio getPortfolioById(@PathVariable(name = "portfolioId") int portfolioId) {
        return portfolioService.getPortfolioById(portfolioId).orElseThrow();
    }

    @PutMapping(path = "/{portfolioId}/order")
    public Portfolio addOrderToPortfolio(@PathVariable(name = "portfolioId")int portfolioId, @RequestBody ClientOrder clientOrder) {
        return portfolioService.addOrderToPortofolio(portfolioId, clientOrder).orElseThrow();
    }

    @PutMapping(path = "/{portfolioId}/update")
    public Portfolio updatePortfolio(@PathVariable(name = "portfolioId") int portfolioId, @RequestBody Portfolio portfolio) {

        return portfolioService.updatePortfolio(portfolioId, portfolio).orElseThrow();
    }

    @DeleteMapping(path = "/{portfolioId}/delete")
    public boolean closePortfolio(@PathVariable(name = "portfolioId") int portfolioId) {
        return portfolioService.closePortfolio(portfolioId);
    }

    @GetMapping(path = "/{portfolioId}/orders")
    public List<ClientOrder> getAllOrders() {
        return portfolioService.getAllOrders();
    }

    @GetMapping(path = "/{portfolioId}/product/{product}")
    public List<ClientOrder> getOrderByProduct(@PathVariable int portfolioId, @PathVariable String product) {
        return portfolioService.getOrdersByProduct(product);
    }

    @GetMapping(path = "/{portfolioId}/status/{status}")
    public List<ClientOrder> getOrderByStatus(@PathVariable(name = "portfolioId")int portfolioId, @PathVariable(name = "status") String status) {
        return portfolioService.getOrderByStatus(status);
    }

    //add portfolio client
    @PutMapping(path = "/{portfolioId}/client")
    public Portfolio addPortfolioClient(@PathVariable(name = "portfolioId")int portfolioId, @RequestBody Client client) {
        return portfolioService.addPortofolioClient(portfolioId, client).orElseThrow();
    }

}
