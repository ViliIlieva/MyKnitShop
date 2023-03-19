package com.example.myknitshop.SheduleJobs;

import com.example.myknitshop.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClearOldOrdersSchedule {
    private final OrderService orderService;

    public ClearOldOrdersSchedule(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void doInBackground(){
        this.orderService.deleteOldOrders ();
    }
}
