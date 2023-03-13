package com.example.TaxiPark.model;

public class OrderInfoDto {

  private String address = "";
  private String quantityOfOrder;
  private String allDistance;
  private String allMoney;

  public OrderInfoDto() {
  }

  public OrderInfoDto(String address, String quantityOfOrder, String allDistance, String allMoney) {
    this.address = address;
    this.quantityOfOrder = quantityOfOrder;
    this.allDistance = allDistance;
    this.allMoney = allMoney;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getQuantityOfOrder() {
    return quantityOfOrder;
  }

  public void setQuantityOfOrder(String quantityOfOrder) {
    this.quantityOfOrder = quantityOfOrder;
  }

  public String getAllDistance() {
    return allDistance;
  }

  public void setAllDistance(String allDistance) {
    this.allDistance = allDistance;
  }

  public String getAllMoney() {
    return allMoney;
  }

  public void setAllMoney(String allMoney) {
    this.allMoney = allMoney;
  }
}
