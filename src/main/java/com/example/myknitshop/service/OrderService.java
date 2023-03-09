package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.bindingModels.MakeOrderDTO;
import com.example.myknitshop.models.entity.Order;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.models.enums.OrderStatusEnum;
import com.example.myknitshop.repository.OrderRepository;
import com.example.myknitshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;

@Service
public class OrderService {


//    public void orderProducts(MakeOrderDTO makeOrderDTO, Principal username) {
//        User client = this.userService.getUserByPrincipal (username);
//        Order order = new Order ();
//
//        order.getOrderedProducts ().addAll (client.getPurchaseProduct ());
//        order.setDateOrdered (LocalDate.now ());
//        order.setClient (client);
//        order.setOrderStatus (OrderStatusEnum.OPEN);
//        order.setOrderSum (this.userService.sumForAllPurchaseProduct (username));
//        this.orderRepository.save (order);
//
//
//
//        client.setAddress (makeOrderDTO.getAddress ());
//        client.setPhoneNumber (makeOrderDTO.getPhoneNumber ());
//        client.getAllBuyProduct ().addAll (client.getPurchaseProduct ());
//        client.getPurchaseProduct ().clear ();
//        client.getOrders ().add (order);
//        this.userRepository.save(client);
//
//    }


//    public void saveNewOrder(Principal username){
//        User client = this.userService.getUserByPrincipal (username);
//        Order order = new Order ();
//
//        order.getOrderedProducts ().addAll (client.getPurchaseProduct ());
//        order.setDateOrdered (LocalDate.now ());
//        order.setClient (client);
//        order.setOrderStatus (OrderStatusEnum.OPEN);
//        order.setOrderSum (this.userService.sumForAllPurchaseProduct (username));
//
//        this.orderRepository.save (order);
//        client.getOrders ().add (order);
//    }

}
