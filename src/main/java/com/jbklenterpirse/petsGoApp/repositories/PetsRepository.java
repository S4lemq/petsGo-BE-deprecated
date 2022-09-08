package com.jbklenterpirse.petsGoApp.repositories;

import com.jbklenterpirse.petsGoApp.repositories.entities.PetEntity;
import com.jbklenterpirse.petsGoApp.repositories.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PetsRepository extends JpaRepository<PetEntity, UUID> {

    void deletePetById(Optional<PetEntity> id);

    PetEntity findPetByName(String name);

    List<PetEntity> getPetsByUser (UserEntity userEntity);
}
