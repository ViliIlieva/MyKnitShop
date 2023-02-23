package com.example.myknitshop.service;

import com.example.myknitshop.models.entity.UserEntity;
import com.example.myknitshop.models.entity.UserRoleEntity;
import com.example.myknitshop.models.enums.UserRoleEnum;
import com.example.myknitshop.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

public class ApplicationUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  public ApplicationUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return
        userRepository.
            findUserEntityByEmail(username).
            map(this::map).
            orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));
  }

  private UserDetails map(UserEntity userEntity) {
    return new User(
        userEntity.getEmail(),
        userEntity.getPassword(),
            (Collection<? extends GrantedAuthority>) extractAuthorities(userEntity)
    );
  }

  private GrantedAuthority extractAuthorities(UserEntity userEntity) {
    return (GrantedAuthority) userEntity.
        getRole().setName(UserRoleEnum.valueOf("ROLE_" + userEntity.getRole()));
  }

  private GrantedAuthority mapRole(UserRoleEntity userRoleEntity) {
    return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getName().name());
  }
}
