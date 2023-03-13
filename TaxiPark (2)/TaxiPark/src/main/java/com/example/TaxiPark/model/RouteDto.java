package com.example.TaxiPark.model;

import java.util.Objects;

public class RouteDto {

    private String endAddress;

    private String startAddress;
    private Integer distance;
    private Integer duration;
    private Double price;

    @Override
    public String toString() {
        return "RouteDto{" +
                "endAddress='" + endAddress + '\'' +
                ", startAddress='" + startAddress + '\'' +
                ", distance=" + distance +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }

    public RouteDto(Integer distance, Integer duration, Double price) {
        this.distance = distance;
        this.duration = duration;
        this.price = price;
    }

    public RouteDto(String endAddress, String startAddress, Integer distance, Integer duration, Double price) {
        this.endAddress = endAddress;
        this.startAddress = startAddress;
        this.distance = distance;
        this.duration = duration;
        this.price = price;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public RouteDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RouteDto routeDto = (RouteDto) o;
        return Objects.equals(endAddress, routeDto.endAddress) && Objects.equals(startAddress,
            routeDto.startAddress) && Objects.equals(distance, routeDto.distance) && Objects.equals(
            duration, routeDto.duration) && Objects.equals(price, routeDto.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endAddress, startAddress, distance, duration, price);
    }
}
