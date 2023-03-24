package com.example.myknitshop.service;


import com.example.myknitshop.models.dto.bindingModels.user.UserRegistrationDTO;
import com.example.myknitshop.models.entity.User;
import com.example.myknitshop.models.enums.UserRoleEnum;
import com.example.myknitshop.repository.UserRepository;
import com.example.myknitshop.repository.UserRoleRepository;
import com.example.myknitshop.util.UserNamePasswordLoginProcessor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserRoleRepository roleRepository;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       ModelMapper modelMapper,
                       UserRoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    public void registerAndLogin(UserRegistrationDTO userRegistrationDTO,
        UserNamePasswordLoginProcessor userNamePasswordLoginProcessor) {

        User newUser = modelMapper.map (userRegistrationDTO, User.class);
        newUser.setPassword (passwordEncoder.encode (userRegistrationDTO.getPassword ()));

        newUser.setUserRoles (roleRepository.findByUserRole (UserRoleEnum.CLIENT));
        userRepository.save (newUser);

        userNamePasswordLoginProcessor.doLogin(userRegistrationDTO.getUsername(),
            userRegistrationDTO.getPassword());
    }
}
