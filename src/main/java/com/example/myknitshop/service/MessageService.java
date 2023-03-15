package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.viewModels.messages.MessagesView;
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
    private final ModelMapper modelMapper;

    public MessageService(MessageRepository messageRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
    }

    public List<MessagesView> getAllMessagesViewOnHomePage() {

        return  this.messageRepository.findAll().stream()
                .map(this::mapMessageDTO)
                .collect(Collectors.toList());
    }

    private MessagesView mapMessageDTO(Message message) {
        MessagesView messageDTO = new MessagesView ();

        messageDTO.setDescription(message.getDescription());
        messageDTO.setAuthor(message.getAuthor());

        return messageDTO;
    }

    public List<MessagesView> getAllMessages() {
        return this.messageRepository.findAll ()
                .stream ()
                .map (m -> {return modelMapper.map (m, MessagesView.class);
                }).toList ();
    }
}