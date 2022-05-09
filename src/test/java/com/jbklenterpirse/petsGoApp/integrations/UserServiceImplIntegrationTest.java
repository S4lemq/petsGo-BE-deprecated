package com.jbklenterpirse.petsGoApp.integrations;

import com.jbklenterpirse.petsGoApp.enums.AuthenticationMessageEnum;
import com.jbklenterpirse.petsGoApp.exceptions.UserAlreadyExistInDatabase;
import com.jbklenterpirse.petsGoApp.exceptions.UserNotFoundException;
import com.jbklenterpirse.petsGoApp.repositories.UserRepository;
import com.jbklenterpirse.petsGoApp.repositories.entities.UserEntity;
import com.jbklenterpirse.petsGoApp.services.UserServiceImpl;
import com.jbklenterpirse.petsGoApp.services.dtos.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.regex.Pattern;

import static com.jbklenterpirse.petsGoApp.enums.ApplicationUserRole.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class UserServiceImplIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String USER_NAME = "userName";
    private static final String USER_PASSWORD = "userPassword";

    @Test
    void should_return_user_with_credentials_from_database() {
        //given
        initDatabase();

        //when
        var result = userService.loadUserByUsername(USER_NAME);

        //then
        assertThat(result.getUsername()).isEqualTo(USER_NAME);
        assertThat(result.getPassword()).isEqualTo(USER_PASSWORD);
        assertThat(result.getAuthorities().toString()).isEqualTo("[" + APP_ADMIN + "]");
    }

    @Test
    void should_save_user_into_database() {
        //given
        UserDto dto = new UserDto();
        dto.setFirstName(FIRST_NAME);
        dto.setLastName(LAST_NAME);
        dto.setUsername(USER_NAME);
        dto.setPassword(USER_PASSWORD);
        dto.setRole(ADMIN);
        var bCryptPrefix = "$2a$10$";
        var bCryptRegex = "^[$]2[abxy]?[$](?:0[4-9]|[12][0-9]|3[01])[$][./0-9a-zA-Z]{53}$";

        //when
        var userId = userService.saveUser(dto);

        //then
        assertThat(userId).isNotNull();
        var userEntityOptional = userRepository.findById(userId);
        var userEntity = userEntityOptional.get();
        assertAll(
                () -> assertThat(userEntity.getFirstName()).isEqualTo(FIRST_NAME),
                () -> assertThat(userEntity.getLastName()).isEqualTo(LAST_NAME),
                () -> assertThat(userEntity.getUsername()).isEqualTo(USER_NAME),
                () -> assertThat(userEntity.getRole()).isEqualTo(ADMIN),
                () -> assertThat(userEntity.getPassword()).contains(bCryptPrefix),
                () -> assertThat(userEntity.getPassword()).matches(Pattern.compile(bCryptRegex))
        );
    }

    @Test
    void should_throw_exception_when_user_is_not_found_in_database() {
        //given
        initDatabase();

        //when
        var result = assertThrows(UserNotFoundException.class,
                () -> userService.loadUserByUsername("fakeUser"));

        //then
        assertThat(result.getMessage()).isEqualTo(AuthenticationMessageEnum.USER_NOT_FOUND.getMessage());
    }

    @Test
    void should_throw_exception_when_user_already_exist_in_database() {
        //given
        initDatabase();
        UserDto dto = new UserDto();
        dto.setUsername(USER_NAME);
        dto.setPassword(USER_PASSWORD);

        //when
        var result = assertThrows(UserAlreadyExistInDatabase.class,
                () -> userService.saveUser(dto));

        //then
        assertThat(result.getMessage()).isEqualTo(AuthenticationMessageEnum.USER_ALREADY_EXIST.getMessage());
    }

    @Test
    void should_throw_exception_when_user_is_unauthorized() {
        //given
        UserEntity entity = new UserEntity();
        entity.setFirstName(FIRST_NAME);
        entity.setLastName(LAST_NAME);
        entity.setUsername(USER_NAME);
        entity.setPassword(USER_PASSWORD);
        entity.setRole(PET_SITTER);
        userRepository.save(entity);

        //when
        //var result = assertThrows()

        //then
    }

    private void initDatabase(){
        UserEntity entity = new UserEntity();
        entity.setFirstName(FIRST_NAME);
        entity.setLastName(LAST_NAME);
        entity.setUsername(USER_NAME);
        entity.setPassword(USER_PASSWORD);
        entity.setRole(ADMIN);

        userRepository.save(entity);
    }
}
