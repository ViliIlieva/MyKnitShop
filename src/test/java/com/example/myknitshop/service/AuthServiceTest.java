package com.example.myknitshop.service;

import com.example.myknitshop.models.dto.bindingModels.user.UserRegistrationDTO;
import com.example.myknitshop.repository.UserRepository;
import com.example.myknitshop.repository.UserRoleRepository;
import com.example.myknitshop.util.UserNamePasswordLoginProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
//    @Mock
//    private PasswordEncoder mockPasswordEncoder;
//    @Mock
//    private ModelMapper mockModelMapper;
//    @Mock
//    private UserRoleRepository mockUserRoleRepository;
//    @Mock
//    private UserRepository mockUserRepository;
//
//    private AuthService toTest;
//
//    @BeforeEach
//    void setUp(){
//        toTest = new AuthService (mockPasswordEncoder, mockUserRepository,
//                mockModelMapper, mockUserRoleRepository);
//    }

//    @Test
//    void TestUserRegistration(){
//        //Arrange
//        String username = "test";
//        String password = "test";
//        String encodedPassword = "encoded_password";
//        String email = "test@example.com";
//        String firstName = "Test";
//        String lastName = "Testov";
//
//        UserRegistrationDTO testRegistrationDTO = new UserRegistrationDTO ()
//                .setUsername (username)
//                .setFirstName (firstName)
//                .setLastName (lastName)
//                .setEmail (email)
//                .setPassword (password);
//
////        UserNamePasswordLoginProcessor testLoginProcessor = new UserNamePasswordLoginProcessor () {
////            @Override
////            public void doLogin(String userName, String password) {
////
////            }
////        }
//
//       when(mockPasswordEncoder.encode (testRegistrationDTO.getPassword ()))
//               .thenReturn (encodedPassword);
//
////       toTest.registerAndLogin (testRegistrationDTO, UserNamePasswordLoginProcessor);
//    }
}
