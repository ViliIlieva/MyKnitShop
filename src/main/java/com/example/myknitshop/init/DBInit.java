package com.example.myknitshop.init;

import com.example.myknitshop.service.InitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

  private final InitService initService;

  public DBInit(InitService initService) {
    this.initService = initService;
  }

  @Override
  public void run(String... args) {
    initService.init();
  }
}
