package com.jbklenterpirse.petsGoApp.integrations;


import com.jbklenterpirse.petsGoApp.enums.PetType;
import com.jbklenterpirse.petsGoApp.repositories.PetsRepository;
import com.jbklenterpirse.petsGoApp.repositories.UserRepository;
import com.jbklenterpirse.petsGoApp.repositories.entities.PetEntity;
import com.jbklenterpirse.petsGoApp.services.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@WithMockUser
public class PetServiceIntergrationTest {

    @Autowired
    private PetsRepository petsRepository;

    @Autowired
    private PetService petService;
    private static final String NAME="name";
    private static final BigDecimal WEIGHT= new BigDecimal(10);
    private static final BigDecimal AGE = new BigDecimal(2);
    private static final PetType TYPE = PetType.DOG;


    private void initDatabase(){
        var petEntity = new PetEntity();
        petEntity.setName(NAME);
        petEntity.setWeight(WEIGHT);
        petEntity.setAge(AGE);
        petEntity.setType(TYPE);
        petsRepository.save(petEntity);
    }

    @Test
    void should_delete_pet_from_database() {
        //given
        initDatabase();
        var pet= petsRepository.findPetByName(NAME);
        //when
        petService.deletePet(pet.getId());
        //then
        assertThat(petsRepository.findAll()).hasSize(0);
        assertThat(petsRepository.findPetByName(NAME)).isEqualTo(null);
    }
}
