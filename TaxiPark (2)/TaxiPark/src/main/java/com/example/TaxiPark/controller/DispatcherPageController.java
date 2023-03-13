package com.example.TaxiPark.controller;

import com.example.TaxiPark.model.entyti.DriverStatus;
import com.example.TaxiPark.model.entyti.Order;
import com.example.TaxiPark.model.entyti.OrderStatus;
import com.example.TaxiPark.model.entyti.User;
import com.example.TaxiPark.service.DriverService;
import com.example.TaxiPark.service.FileService;
import com.example.TaxiPark.service.OrderService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DispatcherPageController {

  private final DriverService driverService;
  private final OrderService orderService;

  private final FileService fileService;

  public DispatcherPageController(DriverService driverService, OrderService orderService, FileService fileService) {
    this.driverService = driverService;
    this.orderService = orderService;
    this.fileService = fileService;
  }

  @GetMapping("/dispatcher")
  public String dispatcher(@AuthenticationPrincipal User user, Model model) {

    model.addAttribute("drivers", driverService.getAllDriverByStatus(DriverStatus.FREE));
    model.addAttribute("formedOrders", orderService.geAllOrderByStatus(OrderStatus.FORMED));

    return "dispatcher";
  }

  @PostMapping("addDriverToOrder")
  public String addDriverToOrder(@AuthenticationPrincipal User user, @RequestParam Long orderId,
      @RequestParam Long driverId) {
    orderService.addDriverToOrder(orderId, driverId);
    return "redirect:/dispatcher";
  }

  @GetMapping("allInfo")
  public String allInfo(@AuthenticationPrincipal User user, Model model) {

    List<Order> allOrders = orderService.getAllOrders();

    List<Order> completeOrder = allOrders.stream().filter(it -> it.getStatus() == OrderStatus.COMPLETE)
        .collect(Collectors.toList());
    List<Order> inProgersOrder =
        allOrders.stream().filter(it -> it.getStatus() == OrderStatus.IN_PROGRESS).collect(Collectors.toList());

    model.addAttribute("completeOrder", completeOrder);
    model.addAttribute("inProgersOrder", inProgersOrder);

    return "dispatcherInfo";
  }

  @PostMapping("ordersInfoBack")
  public String ordersInfo(@AuthenticationPrincipal User user, Model model) {

    return "redirect:/allInfo";
  }

  @PostMapping("orders")
  public String ordersInfoBack(@AuthenticationPrincipal User user, Model model) {

    return "redirect:/dispatcher";
  }

  @PostMapping("generateReport")
  public String generateReport(@AuthenticationPrincipal User user) {

    List<Order> orders = orderService.geAllOrderByStatus(OrderStatus.COMPLETE);
    fileService.generateFile(orders, user);
    return "redirect:/dispatcher";
  }
}
