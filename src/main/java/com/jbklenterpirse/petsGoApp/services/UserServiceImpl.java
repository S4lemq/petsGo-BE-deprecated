package com.jbklenterpirse.petsGoApp.services;

import com.jbklenterpirse.petsGoApp.exceptions.UserAlreadyExistInDatabase;
import com.jbklenterpirse.petsGoApp.exceptions.UserNotFoundException;
import com.jbklenterpirse.petsGoApp.mappers.UserMapper;
import com.jbklenterpirse.petsGoApp.repositories.UserRepository;
import com.jbklenterpirse.petsGoApp.repositories.entities.UserEntity;
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
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var entity = userRepository
                .findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(entity.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(entity.getUsername(), entity.getPassword(), authorities);
    }

    public UUID saveUser(UserDto userDto){
        validateIfUserExists(userDto);
        var userEntity = userMapper.fromDtoToEntity(userDto);
        var savedUserEntity = userRepository.save(userEntity);
        LOGGER.info("Saving new user: " + savedUserEntity.getUsername() + " " + savedUserEntity.getPassword());
        return savedUserEntity.getId();
    }

    public List<UserDto> getAllUsers(){
        var userEntity = (List<UserEntity>) userRepository.findAll();
        return userEntity.stream()
                .map(entity -> userMapper.fromEntityToDto(entity))
                .collect(Collectors.toList());
    }

    private void validateIfUserExists(UserDto userDto) {
        var entity = userRepository.findByUsername(userDto.getUsername());
        if(entity.isPresent()){
            throw new UserAlreadyExistInDatabase();
        }
    }



}
