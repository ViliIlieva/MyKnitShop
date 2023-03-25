package com.example.myknitshop.service;

import com.example.myknitshop.models.entity.Role;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.models.enums.UserRoleEnum;
import com.example.myknitshop.repository.UserRepository;
import junit.framework.AssertionFailedError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
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

        String EXISTING_USERNAME = "admin";
        User testUser = new User().
                setUsername(EXISTING_USERNAME).
                setPassword("admin").
                setUserRoles(List.of(testAdminRole)).
                setEmail ("admin@example.com").
                setFirstName ("Velislava").
                setLastName ("Ilieva");

        when(mockUserRepository.findByUsername(EXISTING_USERNAME)).
                thenReturn(Optional.of (testUser));

        UserDetails adminDetails = toTest.loadUserByUsername (EXISTING_USERNAME);

        Assertions.assertNotNull (adminDetails);
        Assertions.assertEquals (EXISTING_USERNAME, adminDetails.getUsername ());
        Assertions.assertEquals (EXISTING_USERNAME, adminDetails.getUsername ());
        Assertions.assertEquals (testUser.getPassword (), adminDetails.getPassword ());

        Assertions.assertEquals (1,
                adminDetails.getAuthorities ().size (),
                "The authorities are supposed to be just one - ADMIN.");
        assertRole(adminDetails.getAuthorities (), "ROLE_ADMIN");

    }

    private void assertRole(Collection<? extends GrantedAuthority> authorities,
                            String role) {
        authorities.
                stream().
                filter(a -> role.equals(a.getAuthority())).
                findAny().
                orElseThrow(() -> new AssertionFailedError ("Role " + role + " not found!"));
    }

    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(NOT_EXISTING_USERNAME)
        );
    }

}
