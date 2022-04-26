package com.jbklenterpirse.petsGoApp.services;

import com.jbklenterpirse.petsGoApp.mappers.PetsMapper;
import com.jbklenterpirse.petsGoApp.repositories.PetsRepository;
import com.jbklenterpirse.petsGoApp.repositories.entities.PetEntity;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;
import com.jbklenterpirse.petsGoApp.validators.PetValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PetService {

    private final PetsRepository petsRepository;
    private final PetsMapper petsMapper;
    private final PetValidator petValidator;

    public PetService(PetsRepository petsRepository, PetsMapper petsMapper, PetValidator petValidator) {
        this.petsRepository = petsRepository;
        this.petsMapper = petsMapper;
        this.petValidator = petValidator;
    }

    public void setPet(PetDto dto){
        petValidator.validate(dto);
        var entity = petsMapper.fromDtoToEntity(dto);
        petsRepository.save(entity);
    }

    public List<PetDto> getAllPets() {
        var petsEntity = petsRepository.findAll();
        return petsEntity.stream()
                .map(entity -> petsMapper.fromEntityToDto(entity))
                .collect(Collectors.toList());
    }

    public void deletePet(UUID id){
        petsRepository.deleteById(id);
    }
}
