package com.example.TaxiPark.controller;

import com.example.TaxiPark.model.OrderInfoDto;
import com.example.TaxiPark.model.RouteDto;
import com.example.TaxiPark.model.entyti.Order;
import com.example.TaxiPark.model.entyti.User;
import com.example.TaxiPark.service.OrderService;
import com.example.TaxiPark.service.PriceCalculationService;
import com.example.TaxiPark.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserPageController {

  private final PriceCalculationService priceCalculationService;
  private final UserService userService;
  private final OrderService orderService;

  public UserPageController(PriceCalculationService priceCalculationService, UserService userService,
      OrderService orderService) {
    this.priceCalculationService = priceCalculationService;
    this.userService = userService;
    this.orderService = orderService;
  }

  @PostMapping("/priceCalculation")
  public String priceCalculation(@RequestParam String start, @RequestParam String end,
      @AuthenticationPrincipal User user,
      Model model) {
    User updatesUser = userService.getUserById(user.getId());

    RouteDto route = priceCalculationService.getPrice(start, end);
    model.addAttribute(updatesUser);
    Order order = orderService.creteOrder(updatesUser, route);
    model.addAttribute("order", order);
    updatesUser.setCurrentOrderId(order.getId());
    userService.saveUser(updatesUser);
    return "/orderInfo";
  }

  @PostMapping("/priceCalculationToHome")
  public String priceCalculationToHome(@RequestParam String end, @AuthenticationPrincipal User user, Model model) {

    User updatesUser = userService.getUserById(user.getId());

    RouteDto route = priceCalculationService.getPrice(end, updatesUser.getHomeAddress());
    model.addAttribute(updatesUser);
    Order order = orderService.creteOrder(updatesUser, route);
    model.addAttribute("order", order);
    updatesUser.setCurrentOrderId(order.getId());
    userService.saveUser(updatesUser);
    return "/orderInfo";
  }

  @PostMapping("/priceCalculationFromHome")
  public String priceCalculationFromHome(@RequestParam String start, @AuthenticationPrincipal User user, Model model) {

    User updatesUser = userService.getUserById(user.getId());

    RouteDto route = priceCalculationService.getPrice(updatesUser.getHomeAddress(), start);
    model.addAttribute(updatesUser);
    Order order = orderService.creteOrder(updatesUser, route);
    model.addAttribute("order", order);
    updatesUser.setCurrentOrderId(order.getId());
    userService.saveUser(updatesUser);
    return "/orderInfo";
  }

  @PostMapping("/addHomeAddress")
  public String addHomeAddress(@RequestParam String homeAddress, @AuthenticationPrincipal User user) {

    userService.updateHomeAddress(user.getId(), homeAddress);
    return "redirect:/";
  }

  @PostMapping("/addHomeAddressProfile")
  public String addHomeAddressProfile(@RequestParam String homeAddress, @AuthenticationPrincipal User user) {

    userService.updateHomeAddress(user.getId(), homeAddress);
    return "redirect:/userProfile";
  }

  @GetMapping("/comment")
  public String comment(@AuthenticationPrincipal User user) {

    return "userComment";
  }

  @PostMapping("noComment")
  public String noComment(@RequestParam String comment, @AuthenticationPrincipal User user) {
    User userById = userService.getUserById(user.getId());
    orderService.endOrder(userById.getCurrentOrderId());
    return "redirect:/";
  }

  @PostMapping("setComment")
  public String setComment(@RequestParam String comment, @AuthenticationPrincipal User user) {
    User userById = userService.getUserById(user.getId());
    orderService.endOrderWithComment(userById.getCurrentOrderId(), comment);
    return "redirect:/";
  }

  @GetMapping("userProfile")
  public String userProfile(@AuthenticationPrincipal User user, Model model) {

    User userById = userService.getUserById(user.getId());
    model.addAttribute("user", userById);
    OrderInfoDto infoByUser = orderService.getInfoByUser(userById);
    model.addAttribute("infoByUser", infoByUser);
    return "userProfile";
  }

  @PostMapping("toProfile")
  public String toProfile(@AuthenticationPrincipal User user) {
    return "redirect:/userProfile";
  }
}
