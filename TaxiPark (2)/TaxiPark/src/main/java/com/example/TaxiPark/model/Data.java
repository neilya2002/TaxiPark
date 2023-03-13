package com.example.TaxiPark.model;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {
    private List<Point> points;
    private int[] sources;
    private int[] targets;

    public Data() {
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public int[] getSources() {
        return sources;
    }

    public void setSources(int[] sources) {
        this.sources = sources;
    }

    public int[] getTargets() {
        return targets;
    }

    public void setTargets(int[] targets) {
        this.targets = targets;
    }

    public Data(List<Point> points, int[] sources, int[] targets) {
        this.points = points;
        this.sources = sources;
        this.targets = targets;
    }
}