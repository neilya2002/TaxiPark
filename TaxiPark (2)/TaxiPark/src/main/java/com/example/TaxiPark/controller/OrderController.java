package com.example.TaxiPark.controller;

import com.example.TaxiPark.model.entyti.Order;
import com.example.TaxiPark.model.entyti.OrderStatus;
import com.example.TaxiPark.model.entyti.User;
import com.example.TaxiPark.service.DriverService;
import com.example.TaxiPark.service.OrderService;
import com.example.TaxiPark.service.TimeManagerService;
import com.example.TaxiPark.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

  private final OrderService orderService;
  private final DriverService driverService;

  private int minutesToDriver = 2;
  private final UserService userService;

  private final TimeManagerService timeManagerService;

  public OrderController(OrderService orderService, DriverService driverService, UserService userService,
      TimeManagerService timeManagerService) {
    this.orderService = orderService;
    this.driverService = driverService;
    this.userService = userService;
    this.timeManagerService = timeManagerService;
  }

  @GetMapping("orderInfo")
  public String orderInfo(@AuthenticationPrincipal User user, Model model) {

    User updatesUser = userService.getUserById(user.getId());
    model.addAttribute(updatesUser);

    if (updatesUser.getCurrentOrderId() == null) {
      return "/login";
    }

    Order order = orderService.getFormedOrderByUser(updatesUser);

    if (order.getStatus().equals(OrderStatus.CONFIRMED)) {
      minutesToDriver = (int) (Math.random() * 5);
      timeManagerService.driverWait(minutesToDriver, order.getId());
      order.setStatus(OrderStatus.WAITING_DRIVER);
      orderService.saveOrder(order);
    }
    model.addAttribute("minutesToDriver", minutesToDriver);
    model.addAttribute("order", order);
    if (order.getStatus() == OrderStatus.WAITING_COMMENT) {
      return "redirect:/comment";
    }
    return "/orderInfo";
  }
}
