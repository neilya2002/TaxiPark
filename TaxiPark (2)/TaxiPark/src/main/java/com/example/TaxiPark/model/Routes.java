package com.example.TaxiPark.model;

import java.io.Serializable;

public class Routes implements Serializable {

    private Integer distance;
    private Integer duration;

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Routes() {
    }

    @Override
    public String toString() {
        return "Routes{" +
                "distance=" + distance +
                ", duration=" + duration +
                '}';
    }

    public Routes(Integer distance, Integer duration) {
        this.distance = distance;
        this.duration = duration;
    }
}
