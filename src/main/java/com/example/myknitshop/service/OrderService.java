package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.viewModels.orders.CloseOrderToAdminPanelView;
import com.example.myknitshop.models.dto.viewModels.orders.OpenOrderToAdminPanelView;
import com.example.myknitshop.models.entity.Order;
import com.example.myknitshop.models.enums.OrderStatusEnum;
import com.example.myknitshop.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    public List<OpenOrderToAdminPanelView> GetAllOpenOrders() {
        return this.orderRepository.findOrderByOrderStatus (OrderStatusEnum.OPEN)
                .stream ()
                .map (o -> {return modelMapper.map (o, OpenOrderToAdminPanelView.class);
                }).toList ();
    }

    public List<CloseOrderToAdminPanelView> GetAllCloseOrders() {
        return this.orderRepository.findOrderByOrderStatus (OrderStatusEnum.COMPLETED)
                .stream ()
                .map (o -> {return modelMapper.map (o, CloseOrderToAdminPanelView.class);
                }).toList ();
    }

    public void closeOrder(Long orderId) {
        Order order = this.orderRepository.findById (orderId).get ();
        order.setOrderStatus (OrderStatusEnum.COMPLETED);
        this.orderRepository.save (order);
    }

    public Order findById(Long orderId) {
       return this.orderRepository.findById(orderId).get();
    }
}