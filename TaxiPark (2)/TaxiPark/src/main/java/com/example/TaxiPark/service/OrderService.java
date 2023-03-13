package com.example.TaxiPark.service;

import com.example.TaxiPark.model.OrderInfoDto;
import com.example.TaxiPark.model.RouteDto;
import com.example.TaxiPark.model.entyti.Driver;
import com.example.TaxiPark.model.entyti.DriverStatus;
import com.example.TaxiPark.model.entyti.Order;
import com.example.TaxiPark.model.entyti.OrderStatus;
import com.example.TaxiPark.model.entyti.User;
import com.example.TaxiPark.repository.DriverRepos;
import com.example.TaxiPark.repository.OrderRepos;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private final OrderRepos orderRepos;
  private final DriverRepos driverRepos;
  private final UserService userService;

  public OrderService(OrderRepos orderRepos, DriverRepos driverRepos, UserService userService) {
    this.orderRepos = orderRepos;
    this.driverRepos = driverRepos;
    this.userService = userService;
  }

  public List<Order> getAllOrders() {
    return orderRepos.findAll();
  }

  public OrderInfoDto getInfoByUser(User user) {

    List<Order> all = orderRepos.findAll();
    List<Order> collect = all.stream().filter(it -> it.getUser().getId() == user.getId())
        .collect(Collectors.toList());
    OrderInfoDto orderInfoDto = getInfo(collect);
    if (user.getHomeAddress() != null) {
      orderInfoDto.setAddress(user.getHomeAddress());
    }
    return orderInfoDto;
  }

  private OrderInfoDto getInfo(List<Order> orders) {
    OrderInfoDto orderInfoDto = new OrderInfoDto();
    orderInfoDto.setQuantityOfOrder(String.valueOf(orders.size()));
    String distance = String.valueOf(orders.stream().map(it -> it.getDistance())
        .reduce((a, b) -> a + b).get());
    orderInfoDto.setAllDistance(distance);

    String money = String.valueOf(orders.stream().map(it -> it.getPrice())
        .map(BigDecimal::intValue)
        .reduce((a, b) -> a + b).get());

    orderInfoDto.setAllMoney(money);

    return orderInfoDto;
  }

  public void endOrder(Long id) {
    Optional<Order> byId = orderRepos.findById(id);
    Order order = byId.get();
    order.setStatus(OrderStatus.COMPLETE);
    saveOrder(order);
    User user = order.getUser();
    user.setCurrentOrderId(null);
    userService.saveUser(user);
  }

  public void endOrderWithComment(Long id, String comment) {
    Optional<Order> byId = orderRepos.findById(id);
    Order order = byId.get();
    order.setComment(comment);
    order.setStatus(OrderStatus.COMPLETE);
    saveOrder(order);
    User user = order.getUser();
    user.setCurrentOrderId(null);
    userService.saveUser(user);
  }

  public Order getOrderById(Long id) {
    return orderRepos.findById(id).get();
  }

  public List<Order> geAllOrderByStatus(OrderStatus orderStatus) {
    return orderRepos.findAllByStatus(orderStatus);
  }

  public void updateOrderStatus(Long id) {
    Optional<Order> byId = orderRepos.findById(id);
    Order order = byId.get();
    order.setStatus(OrderStatus.IN_PROGRESS);
    orderRepos.save(order);
  }

  public void endOfWork(Order order) {
    order.setStatus(OrderStatus.WAITING_COMMENT);
    orderRepos.save(order);
    Driver driver = driverRepos.findById(order.getDriver().getId()).get();
    driver.setStatus(DriverStatus.FREE);
    driverRepos.save(driver);
  }

  public Order addDriverToOrder(Long orderId, Long driverId) {
    Order order = orderRepos.findById(orderId).get();
    Driver driver = driverRepos.findById(driverId).get();

    order.setDriver(driver);
    order.setStatus(OrderStatus.CONFIRMED);
    driver.setStatus(DriverStatus.IN_PROCESS);

    driverRepos.save(driver);
    return orderRepos.save(order);
  }

  public Order creteOrder(User user, RouteDto routeDto) {
    Order order = buildOrder(user, routeDto);
    return saveOrder(order);
  }

  public Order getFormedOrderByUser(User user) {

    return orderRepos.findById(user.getCurrentOrderId()).get();
  }

  public Order saveOrder(Order order) {
    return orderRepos.save(order);
  }

  private Order buildOrder(User user, RouteDto routeDto) {
    Order order = new Order();
    order.setStatus(OrderStatus.FORMED);
    order.setDistance(routeDto.getDistance());
    order.setEndAddress(routeDto.getEndAddress());
    order.setStartAddress(routeDto.getStartAddress());
    order.setPrice(BigDecimal.valueOf(routeDto.getPrice()));
    order.setTime(timeBuilder(routeDto.getDuration()));
    order.setUser(user);
    return order;
  }

  private String timeBuilder(Integer time) {
    String fullTime = String.valueOf(time / 60);
    String lessTTime = String.valueOf(time % 60);
    return fullTime + "." + lessTTime;
  }
}
