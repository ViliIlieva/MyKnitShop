package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.viewModels.messages.MessagesViewOnHomePageDTO;
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

    public List<MessagesViewOnHomePageDTO> getAllMessages() {

        return  this.messageRepository.findAll().stream()
                .map(this::mapMessageDTO)
                .collect(Collectors.toList());
    }

    private MessagesViewOnHomePageDTO mapMessageDTO(Message message) {
        MessagesViewOnHomePageDTO messageDTO = new MessagesViewOnHomePageDTO ();

        messageDTO.setDescription(message.getDescription());
        messageDTO.setAuthor(message.getAuthor());

        return messageDTO;
    }
}
