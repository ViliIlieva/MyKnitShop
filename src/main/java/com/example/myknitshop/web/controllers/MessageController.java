package com.example.myknitshop.web.controllers;

import com.example.myknitshop.models.dto.bindingModels.MessageDTO;
import com.example.myknitshop.service.MessageService;
import com.example.myknitshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

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
