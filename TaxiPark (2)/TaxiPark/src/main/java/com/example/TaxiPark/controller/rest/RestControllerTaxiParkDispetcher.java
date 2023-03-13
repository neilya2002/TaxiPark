package com.example.TaxiPark.controller.rest;

import com.example.TaxiPark.model.entyti.Driver;
import com.example.TaxiPark.model.entyti.DriverStatus;
import com.example.TaxiPark.model.entyti.Order;
import com.example.TaxiPark.model.entyti.OrderStatus;
import com.example.TaxiPark.repository.DriverRepos;
import com.example.TaxiPark.repository.OrderRepos;
import com.example.TaxiPark.service.DriverService;
import com.example.TaxiPark.service.OrderService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("dispetcher")
public class RestControllerTaxiParkDispetcher {

  private final DriverRepos driverRepos;
  private final DriverService driverService;
  private final OrderService orderService;
  private final OrderRepos orderRepos;

  public RestControllerTaxiParkDispetcher(DriverRepos driverRepos, DriverService driverService,
      OrderService orderService, OrderRepos orderRepos) {
    this.driverRepos = driverRepos;
    this.driverService = driverService;
    this.orderService = orderService;
    this.orderRepos = orderRepos;
  }

  @GetMapping("/orders/all")
  public List<Order> getOrders() {
    return orderRepos.findAll();
  }

  @GetMapping("/orders/{id}")
  public Order getOrder(@PathVariable Long id) {
    return orderRepos.findById(id).get();
  }

  @GetMapping("/orders/status/{status}")
  public List<Order> getOrderByStatus(@PathVariable String status) {
    return orderRepos.findAllByStatus(OrderStatus.valueOf(status));
  }

  @GetMapping("/drivers/all")
  public List<Driver> getSomething() {
    return driverRepos.findAll();
  }

  @GetMapping("/drivers/status/{status}")
  public List<Driver> getSomething(@PathVariable String status) {
    return driverRepos.findAllByStatus(DriverStatus.valueOf(status));
  }

  @GetMapping("/drivers/{id}")
  public Driver getSomething(@PathVariable Long id) {
    return driverRepos.findById(id).get();
  }
}
