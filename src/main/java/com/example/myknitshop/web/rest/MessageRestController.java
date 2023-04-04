package com.example.myknitshop.web.rest;

import com.example.myknitshop.models.dto.viewModels.messages.MessagesView;
import com.example.myknitshop.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageRestController {
    private final MessageService messageService;

    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/api/comments")
    public ResponseEntity<List<MessagesView>> getComments() {
        return ResponseEntity.ok(messageService.getAllMessagesViewOnHomePage());
    }
}
