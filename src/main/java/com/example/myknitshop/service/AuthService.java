package com.example.myknitshop.service;


import com.example.myknitshop.models.dto.bindingModels.UserRegistrationDTO;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.models.enums.UserRoleEnum;
import com.example.myknitshop.repository.UserRepository;
import com.example.myknitshop.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserDetailsService appUserDetailsService;
    private final ModelMapper modelMapper;
    private final UserRoleRepository roleRepository;

    public AuthService(PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       UserDetailsService appUserDetailsService,
                       ModelMapper modelMapper,
                       UserRoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.appUserDetailsService = appUserDetailsService;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    public void registerAndLogin(UserRegistrationDTO userRegistrationDTO) {
//TODO дали трябва да проверявам дали двете пароли съвпадат

        User newUser = modelMapper.map (userRegistrationDTO, User.class);
        newUser.setPassword (passwordEncoder.encode (userRegistrationDTO.getPassword ()));
        newUser.setUserRoles (roleRepository.findByUserRole (UserRoleEnum.CLIENT));
        userRepository.save (newUser);


        UserDetails userDetails =
                appUserDetailsService.loadUserByUsername (newUser.getUsername ());

        Authentication auth = new UsernamePasswordAuthenticationToken (
                userDetails,
                userDetails.getPassword (),
                userDetails.getAuthorities ()
        );

        SecurityContextHolder.
                getContext ().
                setAuthentication (auth);
    }
}
