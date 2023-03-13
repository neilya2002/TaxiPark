package com.example.TaxiPark.model.entyti;


import javax.persistence.*;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    @OneToOne
    private Car car;
    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    public Driver(Long id, String name, String phone, Car car, DriverStatus status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.car = car;
        this.status = status;
    }

    public Driver() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }
}
