package com.example.TaxiPark.model.entyti;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_name")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String startAddress;
    private String endAddress;
    private BigDecimal price;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private Integer distance;
    @OneToOne(fetch = FetchType.EAGER)
    private Driver driver;
    @OneToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String comment;

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Order(Long id, String startAddress, String endAddress, BigDecimal price, String time, Integer distance, Driver driver, User user, OrderStatus status, String comment) {
        this.id = id;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.price = price;
        this.time = time;
        this.distance = distance;
        this.driver = driver;
        this.user = user;
        this.status = status;
        this.comment = comment;
    }

    public Order() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
