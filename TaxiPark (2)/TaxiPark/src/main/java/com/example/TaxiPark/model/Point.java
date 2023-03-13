package com.example.TaxiPark.model;

import java.io.Serializable;

public class Point implements Serializable {
    private Double lat;
    private Double lon;

    public Point(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Point() {
    }
}
