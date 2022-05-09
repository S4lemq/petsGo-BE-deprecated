package com.jbklenterpirse.petsGoApp.controllers;

import com.jbklenterpirse.petsGoApp.services.PetService;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/pets")
public class PetsController {

    private final PetService petService;

    public PetsController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PET_SITTER', 'PET_SEARCHER')")
    public List<PetDto> getPets(){
        return petService.getAllPets();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PET_SEARCHER')")
    public void setPet(@RequestBody PetDto dto){
        petService.setPet(dto);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PET_SEARCHER')")
    public void deletePet(@PathVariable UUID id){
        petService.deletePet(id);
    }
}
