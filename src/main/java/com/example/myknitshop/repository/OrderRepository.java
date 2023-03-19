package com.example.myknitshop.repository;

import com.example.myknitshop.models.entity.Order;
import com.example.myknitshop.models.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrderByOrderStatus(OrderStatusEnum orderStatusEnum);

    List<Order> findOrderByDateOrderedBefore(LocalDate twoYearBack);
}
