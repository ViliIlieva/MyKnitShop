package com.example.myknitshop.models.dto.viewModels.messages;

import com.example.myknitshop.models.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessagesView {
    private Long id;
    private String description;
    private User author;

    public String getDescription() {
        return description;
    }

    public MessagesView setDescription(String description) {
        this.description = description;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public MessagesView setAuthor(User author) {
        this.author = author;
        return this;
    }


}
