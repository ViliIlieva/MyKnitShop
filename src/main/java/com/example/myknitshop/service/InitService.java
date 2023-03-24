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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class InitService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PasswordEncoder passwordEncoder;
    private String adminPass;

    @Autowired
    public InitService(UserRoleRepository userRoleRepository,
                       UserRepository userRepository,
                       @Value("${app.default.admin.password}") String adminPass,
                       CategoryRepository categoryRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminPass = adminPass;
    }

    public void init() {
        initRoles();
        initCategories();
        initUsers();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var clientRole = new Role().setUserRole (UserRoleEnum.ADMIN);
            var adminRole = new Role().setUserRole (UserRoleEnum.CLIENT);

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
                        categoryRepository.save(category);
                    });
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            initAdmin();
            initClient1();
            initClient2();
            initClient3();
            initClient4();
        }
    }

    private void initAdmin() {
        var adminUser = new User().
                setUsername("admin").
                setEmail("admin@example.com").
                setFirstName("Velislava").
                setLastName("Ilieva").
                setPassword(passwordEncoder.encode (adminPass)).
                setUserRoles(userRoleRepository.findByUserRole(UserRoleEnum.ADMIN));

        userRepository.save(adminUser);
    }

    private void initClient1() {
        var clientUser = new User().
                setUsername("client").
                setEmail("client@example.com").
                setFirstName("Branimira").
                setLastName("Ruseva").
                setPassword(passwordEncoder.encode("client")).
                setUserRoles(userRoleRepository.findByUserRole(UserRoleEnum.CLIENT));

        userRepository.save(clientUser);
    }
    private void initClient2() {
        var clientUser = new User().
                setUsername("petar").
                setEmail("petar@example.com").
                setFirstName("Petar").
                setLastName("Petrov").
                setPassword(passwordEncoder.encode("client")).
                setUserRoles(userRoleRepository.findByUserRole(UserRoleEnum.CLIENT));

        userRepository.save(clientUser);
    }
    private void initClient3() {
        var clientUser = new User().
                setUsername("ivan").
                setEmail("ivan@example.com").
                setFirstName("Ivan").
                setLastName("Ivanov").
                setPassword(passwordEncoder.encode("client")).
                setUserRoles(userRoleRepository.findByUserRole(UserRoleEnum.CLIENT));

        userRepository.save(clientUser);
    }
    private void initClient4() {
        var clientUser = new User().
                setUsername("georgi").
                setEmail("georgi@example.com").
                setFirstName("Georgi").
                setLastName("Georgiev").
                setPassword(passwordEncoder.encode("client")).
                setUserRoles(userRoleRepository.findByUserRole(UserRoleEnum.CLIENT));

        userRepository.save(clientUser);
    }

}
