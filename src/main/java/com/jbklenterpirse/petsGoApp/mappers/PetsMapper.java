package com.jbklenterpirse.petsGoApp.mappers;

import com.jbklenterpirse.petsGoApp.builders.PetDtoBuilder;
import com.jbklenterpirse.petsGoApp.builders.PetEntityBuilder;
import com.jbklenterpirse.petsGoApp.repositories.entities.PetEntity;
import com.jbklenterpirse.petsGoApp.repositories.entities.UserEntity;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PetsMapper {

    public PetEntity fromDtoToEntity(PetDto dto, UserEntity user){
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

        if(Objects.nonNull(dto.getGender())){
            entityBuilder.withGender(dto.getGender());
        }

        if(Objects.nonNull(user)){
            entityBuilder.withUser(user);
        }

        return entityBuilder.build();
    }

    public PetDto fromEntityToDto(PetEntity entity){
        if(Objects.isNull(entity)){
            return null;
        }

        var dtoBuilder = new PetDtoBuilder();

        if(Objects.nonNull(entity.getId())){
            dtoBuilder.withId(entity.getId());
        }

        if(Objects.nonNull(entity.getName())){
            dtoBuilder.withName(entity.getName());
        }

        if(Objects.nonNull(entity.getAge())){
            dtoBuilder.withAge(entity.getAge());
        }

        if(Objects.nonNull(entity.getWeight())){
            dtoBuilder.withWeight(entity.getWeight());
        }

        if(Objects.nonNull(entity.getType())){
            dtoBuilder.withType(entity.getType());
        }

        if(Objects.nonNull(entity.getGender())){
            dtoBuilder.withGender(entity.getGender());
        }
        return dtoBuilder.build();
    }

}
