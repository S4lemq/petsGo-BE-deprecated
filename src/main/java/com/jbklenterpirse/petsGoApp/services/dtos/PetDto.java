package com.jbklenterpirse.petsGoApp.services.dtos;

import com.jbklenterpirse.petsGoApp.enums.PetGender;
import com.jbklenterpirse.petsGoApp.enums.PetType;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class PetDto {

    private UUID id;
    private String name;
    private BigDecimal weight;
    private BigDecimal age;
    private PetType type;
    private PetGender gender;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public PetGender getGender() {
        return gender;
    }

    public void setGender(PetGender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetDto petDto = (PetDto) o;
        return Objects.equals(id, petDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PetDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", age=" + age +
                ", type=" + type +
                ", gender=" + gender +
                '}';
    }
}
