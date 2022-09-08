package com.jbklenterpirse.petsGoApp.builders;

import com.jbklenterpirse.petsGoApp.enums.PetGender;
import com.jbklenterpirse.petsGoApp.enums.PetType;
import com.jbklenterpirse.petsGoApp.repositories.entities.PetEntity;
import com.jbklenterpirse.petsGoApp.repositories.entities.UserEntity;

import java.math.BigDecimal;
import java.util.UUID;

public class PetEntityBuilder {
    private UUID id;
    private String name;
    private BigDecimal weight;
    private BigDecimal age;
    private PetType type;
    private PetGender gender;
    private UserEntity userEntity;

    public PetEntityBuilder withId(UUID id){
        this.id = id;
        return this;
    }

    public PetEntityBuilder withName(String name){
        this.name = name;
        return this;
    }

    public PetEntityBuilder withWeight(BigDecimal weight){
        this.weight = weight;
        return this;
    }

    public PetEntityBuilder withAge(BigDecimal age){
        this.age = age;
        return this;
    }

    public PetEntityBuilder withType(PetType type){
        this.type = type;
        return this;
    }

    public PetEntityBuilder withGender(PetGender gender){
        this.gender = gender;
        return this;
    }

    public PetEntityBuilder withUser(UserEntity user) {
        this.userEntity = user;
        return this;
    }

    public PetEntity build(){
        var entity = new PetEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setWeight(this.weight);
        entity.setAge(this.age);
        entity.setType(this.type);
        entity.setGender(this.gender);
        entity.setUser(this.userEntity);
        return entity;
    }
}
