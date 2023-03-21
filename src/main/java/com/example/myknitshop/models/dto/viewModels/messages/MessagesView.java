package com.example.myknitshop.models.dto.viewModels.messages;

import com.example.myknitshop.models.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessagesView {
    private Long id;
    private String description;
    private String authorFullName;

    public String getDescription() {
        return description;
    }

    public MessagesView setDescription(String description) {
        this.description = description;
        return this;
    }


    public Long getId() {
        return id;
    }

    public MessagesView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public MessagesView setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
        return this;
    }
}
