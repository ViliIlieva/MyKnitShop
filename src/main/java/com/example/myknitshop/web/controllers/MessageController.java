package com.example.myknitshop.web.controllers;

import com.example.myknitshop.service.MessageService;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

//    @GetMapping("/message/delete/{id}")
//    public String deleteMessage(@PathVariable("id") Long messageId){
//        this.messageService.deleteMessageById(messageId);
//        return "redirect:/user/admin";
//    }
}
