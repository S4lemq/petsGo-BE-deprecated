package com.jbklenterpirse.petsGoApp.services;

import com.jbklenterpirse.petsGoApp.mappers.PetsMapper;
import com.jbklenterpirse.petsGoApp.repositories.PetsRepository;
import com.jbklenterpirse.petsGoApp.repositories.entities.PetEntity;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;
import com.jbklenterpirse.petsGoApp.validators.PetValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PetService.class);

    private final PetsRepository petsRepository;
    private final PetsMapper petsMapper;
    private final PetValidator petValidator;

    public PetService(PetsRepository petsRepository, PetsMapper petsMapper, PetValidator petValidator) {
        this.petsRepository = petsRepository;
        this.petsMapper = petsMapper;
        this.petValidator = petValidator;
    }

    public void setPet(PetDto dto){
        LOGGER.info("Set pet: " + dto);
        petValidator.validate(dto);
        var entity = petsMapper.fromDtoToEntity(dto);
        petsRepository.save(entity);
        LOGGER.info("Pet saved: " + dto);
    }

    public List<PetDto> getAllPets() {
        LOGGER.info("Get all pets");
        var petsEntity = petsRepository.findAll();
        return petsEntity.stream()
                .map(entity -> petsMapper.fromEntityToDto(entity))
                .collect(Collectors.toList());
    }

    public void deletePet(UUID id){
        LOGGER.info("Delete pet: " + id);
        petsRepository.deleteById(id);
        LOGGER.info("Pet deleted: " + id);
    }

    public void updatePet(PetDto dto) throws UsernameNotFoundException {
        LOGGER.info("Update pet: " + dto);
        petValidator.validate(dto);
        var entity = petsMapper.fromDtoToEntity(dto);
        var petId = entity.getId();
        var petAge = entity.getAge();
        var petWeight = entity.getWeight();
        var petName = entity.getName();
        var petType = entity.getType();
        if(petId==null){
            LOGGER.info("Pet not found in the db");
            throw new UsernameNotFoundException("Pet not found in db");
        }else{
            if(petName==null || petWeight==null || petAge==null || petType==null){
                LOGGER.info("Pet found in db, check your info");
            }else {
                LOGGER.info("Pet found in db: {}", petId);
                petsRepository.save(entity);
                LOGGER.info("Pet updated: " + dto);
            }
        }
    }
}
