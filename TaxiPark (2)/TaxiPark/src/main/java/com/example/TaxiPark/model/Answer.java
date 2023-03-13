package com.example.TaxiPark.model;

import java.io.Serializable;
import java.util.List;

public class Answer implements Serializable {
    private List<Routes> routes;

    public Answer() {
    }

    @Override
    public String toString() {
        return "Answer{" +
                "routes=" + routes +
                '}';
    }

    public List<Routes> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Routes> routes) {
        this.routes = routes;
    }

    public Answer( List<Routes> routes) {
        this.routes = routes;
    }
}
