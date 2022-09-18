package com.jbklenterpirse.petsGoApp.services;

import com.jbklenterpirse.petsGoApp.exceptions.PetAlreadyExistException;
import com.jbklenterpirse.petsGoApp.exceptions.PetNotFoundException;
import com.jbklenterpirse.petsGoApp.mappers.PetsMapper;
import com.jbklenterpirse.petsGoApp.repositories.PetsRepository;
import com.jbklenterpirse.petsGoApp.repositories.entities.PetEntity;
import com.jbklenterpirse.petsGoApp.repositories.entities.UserEntity;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;
import com.jbklenterpirse.petsGoApp.validators.PetValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    public PetEntity setPet(PetDto dto){
        LOGGER.info("Set pet: " + dto);
        if(petsRepository.isExists(dto.getName(), dto.getAge(), dto.getWeight(), dto.getType()))
            throw new PetAlreadyExistException();

        petValidator.validate(dto);
        UserEntity user = getUserEntity();
        var entity = petsMapper.fromDtoToEntity(dto, user);
        petsRepository.save(entity);
        LOGGER.info("Pet saved: " + dto);
        return entity;
    }

    public List<PetDto> getAllPets() {
        LOGGER.info("Get all pets");
        UserEntity user = getUserEntity();
        var petsEntity = petsRepository.getPetsByUser(user);
        return petsEntity.stream()
                .map(petsMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public PetDto getPetById(UUID id){
        UserEntity user = getUserEntity();
        Optional<PetEntity> optionalPet = petsRepository.getPetById(id);
        if(optionalPet.isPresent()) {
            LOGGER.info("Get pet by id: " + id);
            return petsMapper.fromEntityToDto(optionalPet.get());
        } else {
            throw new PetNotFoundException();
        }
    }

    public void deletePet(UUID id){
        Optional<PetEntity> optionalPet = petsRepository.findById(id);
        if(optionalPet.isPresent()) {
            LOGGER.info("Delete pet: " + optionalPet.get());
            petsRepository.deleteById(id);
        } else {
            throw new PetNotFoundException();
        }
    }

    @Transactional
    public void updatePet(PetDto petDto){
        LOGGER.info("Update pet: " + petDto);
        Optional<PetEntity> optionalPet = petsRepository.findById(petDto.getId());
        optionalPet.ifPresent(petEntity -> updatePet(petEntity, petDto));
    }
    private static void updatePet(PetEntity entity, PetDto dto){
        if(Objects.nonNull(dto.getName())
                && !dto.getName().equals(entity.getName())){
            entity.setName(dto.getName());
        }
        if(Objects.nonNull(dto.getWeight())
                && !dto.getWeight().equals(entity.getWeight())){
            entity.setWeight(dto.getWeight());
        }
        if(Objects.nonNull(dto.getAge())
                && !dto.getAge().equals(entity.getAge())){
            entity.setAge(dto.getAge());
        }
        if(Objects.nonNull(dto.getType())
                && !dto.getType().equals(entity.getType())){
            entity.setType(dto.getType());
        }
        if(Objects.nonNull(dto.getGender())
                && !dto.getGender().equals(entity.getGender())){
            entity.setGender(dto.getGender());
        }
    }

    private UserEntity getUserEntity() {
        return userLogInfoService.getLoggedUserEntity();
    }
}
