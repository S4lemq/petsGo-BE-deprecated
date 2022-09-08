package com.jbklenterpirse.petsGoApp;

import com.jbklenterpirse.petsGoApp.builders.PetDtoBuilder;
import com.jbklenterpirse.petsGoApp.builders.PetEntityBuilder;
import com.jbklenterpirse.petsGoApp.enums.PetGender;
import com.jbklenterpirse.petsGoApp.enums.PetType;
import com.jbklenterpirse.petsGoApp.enums.ValidatorsPetEnum;
import com.jbklenterpirse.petsGoApp.exceptions.PetIncompleteException;
import com.jbklenterpirse.petsGoApp.mappers.PetsMapper;
import com.jbklenterpirse.petsGoApp.repositories.PetsRepository;
import com.jbklenterpirse.petsGoApp.repositories.entities.PetEntity;
import com.jbklenterpirse.petsGoApp.services.PetService;
import com.jbklenterpirse.petsGoApp.services.UserLogInfoService;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;
import com.jbklenterpirse.petsGoApp.validators.PetValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PetsServiceTest {

    @Mock
    private PetsRepository petsRepository;
    @Mock
    private UserLogInfoService userLogInfoService;
    private PetValidator petValidator = new PetValidator();
    private final PetsMapper petsMapper = new PetsMapper();

    private PetService petService;

    @BeforeEach
    public void init(){
        petService = new PetService(petsRepository, petsMapper, petValidator, userLogInfoService);
    }


    @Test
    void should_return_list_with_one_pet_if_there_is_one_pet_in_database() {
        //given
        var type = PetType.DOG;
        var name = "nero";
        var weight = BigDecimal.TEN;
        var age = BigDecimal.ONE;
        var gender = PetGender.MALE;
        PetEntity petEntity = new PetEntityBuilder()
                .withType(type)
                .withName(name)
                .withWeight(weight)
                .withAge(age)
                .withGender(gender)
                .build();
        List<PetEntity> petList = Collections.singletonList(petEntity);
        Mockito.when(petsRepository.findAll()).thenReturn(petList);

        //when
        var result = petService.getAllPets();

        //then
        Assertions.assertThat(result)
                .hasSize(1)
                .contains(new PetDtoBuilder()
                        .withType(type)
                        .withName(name)
                        .withWeight(weight)
                        .withAge(age)
                        .withGender(gender)
                        .build());
    }

    @Test
    void should_return_list_with_two_pets_if_there_was_two_pets_in_database() {
        //given
        var typeOne = PetType.DOG;
        var nameOne = "nero";
        var weightOne = BigDecimal.TEN;
        var ageOne = BigDecimal.ONE;
        var genderOne = PetGender.MALE;
        var typeTwo = PetType.CAT;
        var nameTwo = "salem";
        var weightTwo = BigDecimal.ZERO;
        var ageTwo = BigDecimal.ONE;
        var genderTwo = PetGender.FEMALE;
        PetEntity petEntityOne = new PetEntityBuilder()
                .withType(typeOne)
                .withName(nameOne)
                .withWeight(weightOne)
                .withAge(ageOne)
                .withGender(genderOne)
                .build();
        PetEntity petEntityTwo = new PetEntityBuilder()
                .withType(typeTwo)
                .withName(nameTwo)
                .withWeight(weightTwo)
                .withAge(ageTwo)
                .withGender(genderTwo)
                .build();
        List<PetEntity> petEntity = asList(petEntityOne, petEntityTwo);
        Mockito.when(petsRepository.findAll()).thenReturn(petEntity);

        //when
        var result = petService.getAllPets();

        //then
        Assertions.assertThat(result)
                .hasSize(2)
                .containsExactly(new PetDtoBuilder()
                            .withType(typeOne)
                            .withName(nameOne)
                            .withWeight(weightOne)
                            .withAge(ageOne)
                            .withGender(genderOne)
                            .build(),
                        new PetDtoBuilder()
                            .withType(typeTwo)
                            .withName(nameTwo)
                            .withWeight(weightTwo)
                            .withAge(ageTwo)
                            .withGender(genderTwo)
                            .build());
    }


    @Test
    void should_check_if_the_petReposiotry_save_was_called_one_time() {
        //given
        var type = PetType.DOG;
        var name = "nero";
        var weight = BigDecimal.TEN;
        var age = BigDecimal.ONE;
        var gender = PetGender.MALE;
        PetDto petDto = new PetDtoBuilder()
                .withType(type)
                .withName(name)
                .withWeight(weight)
                .withAge(age)
                .withGender(gender)
                .build();
        PetEntity petEntity = new PetEntityBuilder()
                .withType(type)
                .withName(name)
                .withWeight(weight)
                .withAge(age)
                .withGender(gender)
                .build();
        //when
        petService.setPet(petDto);

        //then
        Mockito.verify(petsRepository, Mockito.times(1)).save(petEntity);

    }

    @Test
    void should_throw_exception_when_age_in_pet_is_null() {
        //given
        PetDto pet = new PetDtoBuilder()
                .withName("a")
                .withWeight(BigDecimal.ONE)
                .build();

        //when
        var result = assertThrows(PetIncompleteException.class,
                () -> petService.setPet(pet));

        //then
        assertEquals(ValidatorsPetEnum.NO_AGE.getMessage(), result.getMessage());
    }

    @Test
    void should_throw_exception_when_weight_in_pet_is_null() {
        //given
        PetDto pet = new PetDtoBuilder()
                .withName("a")
                .withAge(BigDecimal.TEN)
                .build();

        //when
        var result = assertThrows(PetIncompleteException.class,
                () -> petService.setPet(pet));

        //then
        assertEquals(ValidatorsPetEnum.NO_WEIGHT.getMessage(), result.getMessage());
    }

    @Test
    void should_throw_exception_when_name_in_pet_is_null() {
        //given
        PetDto pet = new PetDtoBuilder()
                .withAge(BigDecimal.TEN)
                .withWeight(BigDecimal.TEN)
                .build();

        //when
        var result = assertThrows(PetIncompleteException.class,
                () -> petService.setPet(pet));

        //then
        assertEquals(ValidatorsPetEnum.NO_NAME.getMessage(), result.getMessage());
    }

    @Test
    void should_throw_exception_when_name_and_weight_and_height_is_null() {
        //given
        PetDto pet = new PetDto();

        //when
        var result = assertThrows(PetIncompleteException.class,
                () -> petService.setPet(pet));

        //then
        assertEquals(ValidatorsPetEnum.NO_WEIGHT.getMessage() + ", " +
                ValidatorsPetEnum.NO_NAME.getMessage() + ", " + ValidatorsPetEnum.NO_AGE.getMessage(), result.getMessage());
    }


}
