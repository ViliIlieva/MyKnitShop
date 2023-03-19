package com.example.myknitshop.models.dto.viewModels.users;

import com.example.myknitshop.models.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AllUsersView {
    private Long id;
    private String username;
    private String email;
    private List<Role> roles;
}
