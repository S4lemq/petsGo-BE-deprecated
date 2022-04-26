package com.jbklenterpirse.petsGoApp.controllers;

import com.jbklenterpirse.petsGoApp.repositories.entities.PetEntity;
import com.jbklenterpirse.petsGoApp.services.PetService;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pets")
public class PetsController {

    private final PetService petService;

    public PetsController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public List<PetDto> getPets(){
        return petService.getAllPets();
    }

    @PostMapping
    public void setPet(@RequestBody PetDto dto){
        petService.setPet(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePet(@PathVariable UUID id){
        petService.deletePet(id);
    }
}
