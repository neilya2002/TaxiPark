package com.example.TaxiPark.model.entyti;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reports")
public class Report {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToMany
  private List<Order> orders;
  @OneToOne
  private TaxiPark taxiPark;

  private String pathToFile;

  public Report(Long id, List<Order> orders, TaxiPark taxiPark, String pathToFile) {
    this.id = id;
    this.orders = orders;
    this.taxiPark = taxiPark;
      this.pathToFile = pathToFile;
  }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public Report() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  public TaxiPark getTaxiPark() {
    return taxiPark;
  }

  public void setTaxiPark(TaxiPark taxiPark) {
    this.taxiPark = taxiPark;
  }
}
