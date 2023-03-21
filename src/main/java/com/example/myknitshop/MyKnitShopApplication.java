package com.example.myknitshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyKnitShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyKnitShopApplication.class, args);
    }

}
