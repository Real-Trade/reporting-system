package com.realtrade.reportingsystem.models;
import javax.persistence.*;
import java.time.OffsetDateTime;


@Entity
@Table(name = "client_order")
public class ClientOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int orderId;
    private double price;
    private int quantity;
    private String side;
    @JoinColumn(name = "client_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne()
    private Client client;
    private int cumulativeQuantity;
    private  String status;
    private String product;
    private String exchangeOrderId;
    private String exchangeName;
    private OffsetDateTime updatedAt;
    private OffsetDateTime createdAt;
    @JoinColumn(name = "portfolio_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne()
    private Portfolio portfolio;

    public ClientOrder() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    public ClientOrder(double price, int quantity, String side, int cumulativeQuantity,
                       String status, String product, String exchangeName) {
        this.price = price;
        this.quantity = quantity;
        this.side = side;
        this.status = status;
        this.product = product;
        this.cumulativeQuantity = cumulativeQuantity;
        this.exchangeName = exchangeName;
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Client getClientId() {
        return client;
    }

    public void setClientId(Client client) {
        this.client = client;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getExchangeOrderId() {
        return exchangeOrderId;
    }

    public void setExchangeOrderId(String exchangeOrderId) {
        this.exchangeOrderId = exchangeOrderId;
    }

    public int getCumulativeQuantity() {
        return cumulativeQuantity;
    }

    public void setCumulativeQuantity(int cumulativeQuantity) {
        this.cumulativeQuantity = cumulativeQuantity;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
}
