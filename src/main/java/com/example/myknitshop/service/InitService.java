package com.example.myknitshop.service;


import com.example.myknitshop.models.entity.Category;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.models.entity.Role;
import com.example.myknitshop.models.enums.CategoryEnum;
import com.example.myknitshop.models.enums.UserRoleEnum;
import com.example.myknitshop.repository.CategoryRepository;
import com.example.myknitshop.repository.UserRepository;
import com.example.myknitshop.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class InitService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public InitService(UserRoleRepository userRoleRepository,
                       UserRepository userRepository,
                       @Value("owner") String defaultPassword,
                       CategoryRepository categoryRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initCategories();
        initUsers();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var clientRole = new Role().setName(UserRoleEnum.CLIENT);
            var adminRole = new Role().setName(UserRoleEnum.ADMIN);

            userRoleRepository.save(clientRole);
            userRoleRepository.save(adminRole);
        }
    }

    private void initCategories() {
        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryEnum.values())
                    .forEach(categoryName -> {
                        Category category = new Category();
                        category.setName(categoryName);
                        switch (categoryName) {
                            case HAT -> category.setNeededDay(2);
                            case SLIPPERS -> category.setNeededDay(3);
                            case BLANKET -> category.setNeededDay(5);

                        }
                        categoryRepository.save(category);
                    });
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            initAdmin();
            initClient();
        }
    }

    private void initAdmin() {
        var adminUser = new User().
                setUsername("owner").
                setEmail("owner@example.com").
                setFirstName("Velislava").
                setLastName("Ilieva").
                setPassword("owner").
                setRole(userRoleRepository.findByName(UserRoleEnum.ADMIN));

        userRepository.save(adminUser);
    }

    private void initClient() {
        var clientUser = new User().
                setUsername("client").
                setEmail("client@example.com").
                setFirstName("Petar").
                setLastName("Petrov").
                setPassword("client").
                setRole(userRoleRepository.findByName(UserRoleEnum.CLIENT));

        userRepository.save(clientUser);
    }

}
