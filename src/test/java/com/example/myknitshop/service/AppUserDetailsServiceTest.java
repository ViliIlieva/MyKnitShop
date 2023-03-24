package com.example.myknitshop.service;

import com.example.myknitshop.models.entity.Role;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.models.enums.UserRoleEnum;
import com.example.myknitshop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppUserDetailsServiceTest {
    private final String NOT_EXISTING_USERNAME = "notExisting";
    private AppUserDetailsService toTest;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new AppUserDetailsService(
                mockUserRepository
        );
    }

    @Test
    void TestUserFound(){
        Role testAdminRole = new Role().setUserRole(UserRoleEnum.ADMIN);
//        Role testUserRole = new Role().setUserRole(UserRoleEnum.CLIENT);

        String EXISTING_USERNAME = "admin";
        User testUser = new User().
                setUsername(EXISTING_USERNAME).
                setPassword("admin").
                setUserRoles(List.of(testAdminRole));

        when(mockUserRepository.findByUsername(EXISTING_USERNAME)).
                thenReturn(Optional.of(testUser));




    }

    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(NOT_EXISTING_USERNAME)
        );
    }

}
