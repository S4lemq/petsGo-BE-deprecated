package com.jbklenterpirse.petsGoApp.services;

import com.jbklenterpirse.petsGoApp.exceptions.PetNotFoundException;
import com.jbklenterpirse.petsGoApp.mappers.PetsMapper;
import com.jbklenterpirse.petsGoApp.repositories.PetsRepository;
import com.jbklenterpirse.petsGoApp.repositories.entities.UserEntity;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;
import com.jbklenterpirse.petsGoApp.validators.PetValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final UserLogInfoService userLogInfoService;

    public PetService(PetsRepository petsRepository,
                      PetsMapper petsMapper,
                      PetValidator petValidator,
                      UserLogInfoService userLogInfoService) {
        this.petsRepository = petsRepository;
        this.petsMapper = petsMapper;
        this.petValidator = petValidator;
        this.userLogInfoService = userLogInfoService;
    }

    public void setPet(PetDto dto){
        LOGGER.info("Set pet: " + dto);
        petValidator.validate(dto);
        UserEntity user = getUserEntity();
        var entity = petsMapper.fromDtoToEntity(dto, user);
        petsRepository.save(entity);
        LOGGER.info("Pet saved: " + dto);
    }

    public List<PetDto> getAllPets() {
        LOGGER.info("Get all pets");
        UserEntity user = getUserEntity();
        var petsEntity = petsRepository.getPetsByUser(user);
        return petsEntity.stream()
                .map(entity -> petsMapper.fromEntityToDto(entity))
                .collect(Collectors.toList());
    }

    public void deletePet(UUID id){
        var petId = petsRepository.findById(id);
        if(petId!=null) {
            LOGGER.info("Delete pet: " + id);
            petsRepository.deleteById(id);
            LOGGER.info("Pet deleted: " + id);
        }
        else {
            throw new PetNotFoundException();
        }
    }

    public void updatePet(PetDto dto) throws PetNotFoundException {
        LOGGER.info("Update pet: " + dto);
        petValidator.validate(dto);
        UserEntity user = getUserEntity();
        var entity = petsMapper.fromDtoToEntity(dto, user);
        var petId = entity.getId();
        var petAge = entity.getAge();
        var petWeight = entity.getWeight();
        var petName = entity.getName();
        var petType = entity.getType();
        if(petId==null){
            LOGGER.info("Pet not found in the db");
            throw new PetNotFoundException();
        }else{
//            TODO: create genderValidator, use PetValidator in update method
            //if(){
            //    LOGGER.info("Pet found in db, check your info");
           // }else {
                LOGGER.info("Pet found in db: {}", petId);
                petsRepository.save(entity);
                LOGGER.info("Pet updated: " + dto);
           // }
        }
    }

    private UserEntity getUserEntity() {
        LOGGER.info("Get Logged User Entity");
        return userLogInfoService.getLoggedUserEntity();
    }
}
