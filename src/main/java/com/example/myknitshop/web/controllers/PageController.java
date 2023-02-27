package com.example.myknitshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/product")
    public String product() {
        return "product";
    }
}
