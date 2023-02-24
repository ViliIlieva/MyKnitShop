package com.example.myknitshop.repository;

import com.example.myknitshop.models.entity.Role;
import com.example.myknitshop.models.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<Role, Long> {
    Role findByName(UserRoleEnum admin);


}
