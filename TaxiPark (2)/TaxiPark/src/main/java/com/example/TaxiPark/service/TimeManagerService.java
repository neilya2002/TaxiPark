package com.example.TaxiPark.service;

import com.example.TaxiPark.model.entyti.Order;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

@Service
public class TimeManagerService {

  private OrderService orderService;

  ExecutorService executor = Executors.newFixedThreadPool(10);

  public TimeManagerService(OrderService orderService) {
    this.orderService = orderService;
  }

  public void driverWait(Integer minutes, Long orderId) {

    Runnable driverWaitTask = () -> {
      try {
        System.out.println("Minutes" + minutes + "id ordera" + orderId);
        TimeUnit.MINUTES.sleep(minutes);
        orderService.updateOrderStatus(orderId);
        rideInProgress(orderId);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };

    executor.submit(driverWaitTask);
  }

  private void rideInProgress(Long orderId) {
    Order order = orderService.getOrderById(orderId);
    try {
      TimeUnit.MINUTES.sleep((long) Double.parseDouble(order.getTime()));
      orderService.endOfWork(order);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
