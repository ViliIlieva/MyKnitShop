package com.example.myknitshop.models.dto.viewModels.messages;

import com.example.myknitshop.models.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessagesView {
    private String description;
    private String authorFullName;

}
