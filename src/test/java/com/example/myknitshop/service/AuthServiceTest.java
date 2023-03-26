package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.bindingModels.user.UserRegistrationDTO;
import com.example.myknitshop.models.entity.Role;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.models.enums.UserRoleEnum;
import com.example.myknitshop.repository.UserRepository;
import com.example.myknitshop.repository.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    private final String NEW_USERNAME = "plamita";
    private final String RAW_PASSWORD = "plamita";
    private final String ENCODED_PASSWORD = "%($)GGPPP3fdfd";

    private final Long VALID_ID = 1L;
    private final Long INVALID_ID = 300L;

    private final String FIRST_NAME = "Plamena";
    private final String LAST_NAME = "Plamenova";
    private final String EMAIL = "plamita@exmple.com";
    private final String NON_EXISTING_USERNAME = "plamkata";

    @Mock
    private PasswordEncoder mockPasswordEncoder;
    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private UserRoleRepository mockUserRoleRepository;
    @Mock
    private UserRepository mockUserRepository;
    @Captor
    private ArgumentCaptor<User> userEntityArgumentCaptor;

    private AuthService toTest;

    @BeforeEach
    void setUp() {
        toTest = new AuthService (mockPasswordEncoder, mockUserRepository,
                mockModelMapper, mockUserRoleRepository);
    }

    @Test
    void TestUserRegistration() {
        //Arrange
        Role testRole = new Role ();
        testRole.setUserRole (UserRoleEnum.CLIENT);

        UserRegistrationDTO testRegistrationDTO = new UserRegistrationDTO ()
                .setUsername (NEW_USERNAME)
                .setFirstName (FIRST_NAME)
                .setLastName (LAST_NAME)
                .setEmail (EMAIL)
                .setPassword (RAW_PASSWORD);

        User testUser = User.builder ()
                .username (NEW_USERNAME)
                .userRoles (List.of (testRole))
                .email (EMAIL)
                .firstName (FIRST_NAME)
                .lastName (LAST_NAME)
                .password (mockPasswordEncoder.encode (RAW_PASSWORD))
                .build ();

        when (mockPasswordEncoder.encode (testRegistrationDTO.getPassword ()))
                .thenReturn (ENCODED_PASSWORD);
        when (mockModelMapper.map (testRegistrationDTO, User.class)).thenReturn (testUser);
        when (mockUserRoleRepository.findByUserRole (UserRoleEnum.CLIENT)).thenReturn (List.of (testRole));
        toTest.registerUser (testRegistrationDTO);

        Mockito.verify (mockUserRepository).save (userEntityArgumentCaptor.capture());
        Assertions.assertEquals (FIRST_NAME, testUser.getFirstName ());
        Assertions.assertEquals (ENCODED_PASSWORD, testUser.getPassword ());
        Assertions.assertEquals (1, testUser.getUserRoles ().size ());
    }
}
