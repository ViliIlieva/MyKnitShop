package com.example.myknitshop.models.dto.viewModels.messages;

import com.example.myknitshop.models.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessagesView {
    private String description;
    private String authorFullName;

}
