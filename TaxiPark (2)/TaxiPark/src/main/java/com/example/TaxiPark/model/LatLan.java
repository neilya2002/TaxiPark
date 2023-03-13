package com.example.TaxiPark.model;

import java.util.Objects;

public class LatLan {

    private String lat;
    private String lon;


    public LatLan(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public LatLan() {
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "LatLan{" +
            "lat='" + lat + '\'' +
            ", lon='" + lon + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LatLan latLan = (LatLan) o;
        return Objects.equals(lat, latLan.lat) && Objects.equals(lon, latLan.lon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon);
    }
}
