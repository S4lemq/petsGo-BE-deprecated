package com.jbklenterpirse.petsGoApp.repositories;

import com.jbklenterpirse.petsGoApp.repositories.entities.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PetsRepository extends JpaRepository<PetEntity, UUID> {

    void deletePetById(Optional<PetEntity> id);
}
