package com.example.myknitshop.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message extends BaseEntity{

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private User author;

}
