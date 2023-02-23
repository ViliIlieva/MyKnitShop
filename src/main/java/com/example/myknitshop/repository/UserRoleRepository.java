package com.example.myknitshop.repository;

import com.example.myknitshop.models.entity.UserRoleEntity;
import com.example.myknitshop.models.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findByName(UserRoleEnum admin);


}
