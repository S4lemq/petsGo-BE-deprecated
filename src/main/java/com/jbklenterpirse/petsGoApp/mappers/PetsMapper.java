package com.jbklenterpirse.petsGoApp.mappers;

import com.jbklenterpirse.petsGoApp.builders.PetEntityBuilder;
import com.jbklenterpirse.petsGoApp.repositories.entities.PetEntity;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PetsMapper {

    public PetEntity fromDtoToEntity(PetDto dto){
        if(Objects.isNull(dto)){
            return null;
        }

        var entityBuilder = new PetEntityBuilder();

        if(Objects.nonNull(dto.getId())){
            entityBuilder.withId(dto.getId());
        }

        if(Objects.nonNull(dto.getName())){
            entityBuilder.withName(dto.getName());
        }

        if(Objects.nonNull(dto.getAge())){
            entityBuilder.withAge(dto.getAge());
        }

        if(Objects.nonNull(dto.getWeight())){
            entityBuilder.withWeight(dto.getWeight());
        }

        if(Objects.nonNull(dto.getType())){
            entityBuilder.withType(dto.getType());
        }

        return entityBuilder.build();
    }

}
