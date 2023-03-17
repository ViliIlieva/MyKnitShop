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
    private final UserService userService;


    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @ModelAttribute("messageAddDTO")
    public MessageDTO initAddProductDTO(){
        return new MessageDTO();
    }

    @GetMapping("/message/delete/{id}")
    public String deleteMessage(@PathVariable("id") Long messageId){
        this.messageService.deleteMessageById(messageId);
        return "redirect:/user/admin";
    }

    @GetMapping("/message/add/{id}")
    public String addMessage(){
        return "orders";
    }

    @PostMapping("/message/add/{id}")
    public String addMessage(@PathVariable Long orderId, Principal principal,
                             @Valid MessageDTO messageDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("messageDTO", messageDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.messageDTO", bindingResult);

            return "redirect:/orders/add";
        }
        this.userService.addMessage(messageDTO, orderId, principal);
        return "redirect:/orders/add";
    }
}
