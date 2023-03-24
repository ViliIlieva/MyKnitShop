package com.example.myknitshop.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message extends BaseEntity{

    @Column(nullable = false)
    @Length(max = 5000)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private User author;
}
