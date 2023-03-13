package com.example.myknitshop.config;

import com.example.myknitshop.models.entity.ChoseProducts;
import com.example.myknitshop.models.entity.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
