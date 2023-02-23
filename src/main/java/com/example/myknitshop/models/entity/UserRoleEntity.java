package com.example.myknitshop.models.entity;

import jakarta.persistence.*;
import com.example.myknitshop.models.enums.UserRoleEnum;


@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum name;

    public UserRoleEnum getName() {
        return name;
    }

    public UserRoleEntity setName(UserRoleEnum role) {
        this.name = role;
        return this;
    }
}
