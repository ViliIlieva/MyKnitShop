package com.example.myknitshop.service;

import com.example.myknitshop.models.entity.Order;
import com.example.myknitshop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return this.orderRepository.findAll ();
    }



}
