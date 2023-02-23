package com.example.myknitshop.service;


import com.example.myknitshop.models.entity.UserEntity;
import com.example.myknitshop.models.entity.UserRoleEntity;
import com.example.myknitshop.models.enums.UserRoleEnum;
import com.example.myknitshop.repository.UserRepository;
import com.example.myknitshop.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitService(UserRoleRepository userRoleRepository,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       @Value("owner") String defaultPassword) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initUsers();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var clientRole = new UserRoleEntity().setName(UserRoleEnum.CLIENT);
            var adminRole = new UserRoleEntity().setName(UserRoleEnum.ADMIN);

            userRoleRepository.save(clientRole);
            userRoleRepository.save(adminRole);
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            initAdmin();
            initClient();
        }
    }

    private void initAdmin() {
        var adminUser = new UserEntity().
                setUsername("owner").
                setEmail("owner@example.com").
                setFirstName("Velislava").
                setLastName("Ilieva").
                setPassword(passwordEncoder.encode("owner")).
                setRole(userRoleRepository.findByName(UserRoleEnum.ADMIN));

        userRepository.save(adminUser);
    }

    private void initClient() {
        var clientUser = new UserEntity().
                setUsername("client").
                setEmail("client@example.com").
                setFirstName("Petar").
                setLastName("Petrov").
                setPassword(passwordEncoder.encode("client")).
                setRole(userRoleRepository.findByName(UserRoleEnum.CLIENT));

        userRepository.save(clientUser);
    }

}
