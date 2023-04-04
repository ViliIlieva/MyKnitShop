package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.viewModels.orders.CloseOrderToAdminPanelView;
import com.example.myknitshop.models.dto.viewModels.orders.OpenOrderToAdminPanelView;
import com.example.myknitshop.models.entity.Order;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.models.enums.OrderStatusEnum;
import com.example.myknitshop.repository.OrderRepository;
import com.example.myknitshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public List<OpenOrderToAdminPanelView> GetAllOpenOrders() {
        return this.orderRepository.findOrderByOrderStatus (OrderStatusEnum.OPEN)
                .stream ()
                .map (o -> {
                    return modelMapper.map (o, OpenOrderToAdminPanelView.class);
                }).toList ();
    }

    public List<CloseOrderToAdminPanelView> GetAllCloseOrders() {
        return this.orderRepository.findOrderByOrderStatus (OrderStatusEnum.COMPLETED)
                .stream ()
                .map (o -> {
                    return modelMapper.map (o, CloseOrderToAdminPanelView.class);
                }).toList ();
    }

    public void closeOrder(Long orderId) {
        Order order = this.orderRepository.findById (orderId)
                .orElseThrow(() -> new Error("Order not found!"));
        order.setOrderStatus (OrderStatusEnum.COMPLETED);
        this.orderRepository.save (order);
    }

    public Order findById(Long orderId) {
        return this.orderRepository.findById (orderId)
                .orElseThrow(() -> new Error("Order not found!"));
    }

    @Transactional
    public void deleteOldOrders() {
        LocalDate date = LocalDate.now ();
        LocalDate oldDate = date.minus (2, ChronoUnit.YEARS);
        List<Order> orderToDelete = this.orderRepository.findOrderByDateOrderedBefore (oldDate);

        orderToDelete.forEach (order -> {
            User user = this.userRepository.findByUsername (order.getClient ().getUsername ())
                    .orElseThrow(() -> new Error("User not found!"));
            user.getOrders ().remove (order);

            this.orderRepository.deleteById (order.getId ());
        });
    }
}