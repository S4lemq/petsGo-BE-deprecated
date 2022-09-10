package com.jbklenterpirse.petsGoApp.repositories;

import com.jbklenterpirse.petsGoApp.enums.PetType;
import com.jbklenterpirse.petsGoApp.repositories.entities.PetEntity;
import com.jbklenterpirse.petsGoApp.repositories.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface PetsRepository extends JpaRepository<PetEntity, UUID> {

    PetEntity findPetByName(String name);

    List<PetEntity> getPetsByUser (UserEntity userEntity);

    @Query("SELECT count(p.id) > 0 FROM PetEntity p" +
            " WHERE p.name = :name AND p.age = :age AND p.weight = :weight AND p.type = :type")
    boolean isExists(String name, BigDecimal age, BigDecimal weight, PetType type);

}
