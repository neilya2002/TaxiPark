package com.example.TaxiPark.repository;

import com.example.TaxiPark.model.entyti.Order;
import com.example.TaxiPark.model.entyti.OrderStatus;
import com.example.TaxiPark.model.entyti.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepos extends JpaRepository<Order, Long> {

    List<Order> findAllByStatus(OrderStatus status);

    Order findOrderByUserAndStatus(User user, OrderStatus orderStatus);
}
