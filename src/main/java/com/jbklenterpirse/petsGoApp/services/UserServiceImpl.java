package com.jbklenterpirse.petsGoApp.services;

import com.jbklenterpirse.petsGoApp.exceptions.UserNotFoundException;
import com.jbklenterpirse.petsGoApp.mappers.RoleMapper;
import com.jbklenterpirse.petsGoApp.mappers.UserMapper;
import com.jbklenterpirse.petsGoApp.repositories.RoleRepository;
import com.jbklenterpirse.petsGoApp.repositories.UserRepository;
import com.jbklenterpirse.petsGoApp.repositories.entities.UserEntity;
import com.jbklenterpirse.petsGoApp.services.dtos.RoleDto;
import com.jbklenterpirse.petsGoApp.services.dtos.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder, UserMapper userMapper, RoleMapper roleMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var entity = userRepository
                .findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        entity.getRoles().forEach(role ->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(entity.getUsername(), entity.getPassword(), authorities);
    }

    public UUID saveUser(UserDto userDto){
        var userEntity = userMapper.fromDtoToEntity(userDto);
        var savedUserEntity = userRepository.save(userEntity);
        LOGGER.info("Saving new user: " + savedUserEntity.getUsername() + " " + savedUserEntity.getPassword());
        return savedUserEntity.getId();
    }

    public List<UserEntity> getUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

}
