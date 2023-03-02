package com.example.myknitshop.models.dto.viewModels;

import com.example.myknitshop.models.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessagesViewOnHomePageDTO {
    private String description;
    private User author;

    public String getDescription() {
        return description;
    }

    public MessagesViewOnHomePageDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public MessagesViewOnHomePageDTO setAuthor(User author) {
        this.author = author;
        return this;
    }


}
