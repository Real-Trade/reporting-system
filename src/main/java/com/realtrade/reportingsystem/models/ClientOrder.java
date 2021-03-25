package com.realtrade.reportingsystem.models;
import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "client_order")
public class ClientOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer orderId;

    private Double price;

    private int quantity;

    private String side;

    @JoinColumn(name = "client_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne()
    private Client client;

    private  String status;

    private String product;

    private String exchangeOrderId;

    private String exchangeName;

    private LocalDate updatedAt;

    private LocalDate createdAt;

    @JoinColumn(name = "portfolio_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne()
    private Portfolio portfolio;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
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

}
