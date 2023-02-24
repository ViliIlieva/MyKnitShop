package com.example.myknitshop.models.entity;

import jakarta.persistence.*;
import com.example.myknitshop.models.enums.UserRoleEnum;


@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum name;

    public UserRoleEnum getName() {
        return name;
    }

    public Role setName(UserRoleEnum role) {
        this.name = role;
        return this;
    }
}
