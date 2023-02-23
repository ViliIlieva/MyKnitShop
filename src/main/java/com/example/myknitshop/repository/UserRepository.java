package com.example.myknitshop.repository;

import com.example.myknitshop.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByEmail(String email);

//    @Query("SELECT u FROM User u ORDER BY size(u.orders) DESC")
//    List<User> findCountOfOrdersByEmployee();

}
