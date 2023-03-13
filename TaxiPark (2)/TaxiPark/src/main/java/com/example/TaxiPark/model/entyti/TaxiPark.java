package com.example.TaxiPark.model.entyti;

import javax.persistence.*;

@Entity
public class TaxiPark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Dispatcher dispatcher;
    private String name;
    private String phone;

    public TaxiPark() {
    }

    public TaxiPark(Long id, Dispatcher dispatcher, String name, String phone) {
        this.id = id;
        this.dispatcher = dispatcher;
        this.name = name;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
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
}
