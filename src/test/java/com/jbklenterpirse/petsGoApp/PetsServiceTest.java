package com.jbklenterpirse.petsGoApp;

import com.jbklenterpirse.petsGoApp.mappers.PetsMapper;
import com.jbklenterpirse.petsGoApp.repositories.PetsRepository;
import com.jbklenterpirse.petsGoApp.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PetsServiceTest {

    @Mock
    private PetsRepository petsRepository;
    private PetsMapper petsMapper = new PetsMapper();

    private PetService petService;

    @BeforeEach
    public void init(){
        petService = new PetService(petsRepository, petsMapper);
    }


}
