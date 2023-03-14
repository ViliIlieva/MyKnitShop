package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.viewModels.messages.MessagesViewOnHomePageView;
import com.example.myknitshop.models.entity.Message;
import com.example.myknitshop.repository.MessageRepository;
import com.example.myknitshop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<MessagesViewOnHomePageView> getAllMessagesViewOnHomePage() {

        return  this.messageRepository.findAll().stream()
                .map(this::mapMessageDTO)
                .collect(Collectors.toList());
    }

    private MessagesViewOnHomePageView mapMessageDTO(Message message) {
        MessagesViewOnHomePageView messageDTO = new MessagesViewOnHomePageView();

        messageDTO.setDescription(message.getDescription());
        messageDTO.setAuthor(message.getAuthor());

        return messageDTO;
    }

    public List<Message> getAllMessages() {
        return this.messageRepository.findAll ();
    }
}
