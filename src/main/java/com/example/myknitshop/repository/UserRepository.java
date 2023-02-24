package com.example.myknitshop.repository;

import com.example.myknitshop.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    User findByUsername(String username);


//    @Query("SELECT u FROM User u ORDER BY size(u.orders) DESC")
//    List<User> findCountOfOrdersByEmployee();

}
