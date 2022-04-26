package com.jbklenterpirse.petsGoApp.builders;

import com.jbklenterpirse.petsGoApp.enums.PetType;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;

import java.math.BigDecimal;
import java.util.UUID;

public class PetDtoBuilder {
    private UUID id;
    private String name;
    private BigDecimal weight;
    private BigDecimal age;
    private PetType type;

    public PetDtoBuilder withId(UUID id){
        this.id = id;
        return this;
    }

    public PetDtoBuilder withName(String name){
        this.name = name;
        return this;
    }

    public PetDtoBuilder withWeight(BigDecimal weight){
        this.weight = weight;
        return this;
    }

    public PetDtoBuilder withAge(BigDecimal age){
        this.age = age;
        return this;
    }

    public PetDtoBuilder withType(PetType type){
        this.type = type;
        return this;
    }

    public PetDto build(){
        var dto = new PetDto();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setWeight(this.weight);
        dto.setAge(this.age);
        dto.setType(this.type);
        return dto;
    }

}
