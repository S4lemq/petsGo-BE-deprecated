package com.jbklenterpirse.petsGoApp.controllers;

import com.jbklenterpirse.petsGoApp.services.PetService;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.jbklenterpirse.petsGoApp.enums.ApplicationUserRole.*;


@RestController
@RequestMapping("/api/pets")
@CrossOrigin
public class PetsController {

    private final PetService petService;

    public PetsController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('" +
            APP_PET_SEARCHER + "','" +
            APP_PET_SITTER + "','" +
            APP_ADMIN  + "')")
    public List<PetDto> getPets(){
        return petService.getAllPets();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('" +
            APP_PET_SEARCHER + "','" +
            APP_ADMIN  + "')")
    public void setPet(@RequestBody PetDto dto){
        petService.setPet(dto);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('" +
            APP_PET_SEARCHER + "','" +
            APP_ADMIN  + "')")
    public void deletePet(@PathVariable UUID id){
        petService.deletePet(id);
    }

    @PutMapping("/update")
    public void updatePet(@RequestBody PetDto dto) {petService.updatePet(dto);}
}
