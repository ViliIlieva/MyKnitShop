package com.example.myknitshop.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "messages")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message extends BaseEntity  {

    @Column(nullable = false)
    @Length(max = 5000)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private User author;
}
