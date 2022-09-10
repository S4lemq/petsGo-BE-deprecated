package com.jbklenterpirse.petsGoApp.controllers;
import com.jbklenterpirse.petsGoApp.repositories.entities.PetEntity;
import com.jbklenterpirse.petsGoApp.services.PetService;
import com.jbklenterpirse.petsGoApp.services.dtos.PetDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PetDto>> getPets(){
        List<PetDto> allPets = petService.getAllPets();
        return new ResponseEntity<>(allPets, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('" +
            APP_PET_SEARCHER + "','" +
            APP_ADMIN  + "')")
    public ResponseEntity<PetEntity> setPet(@RequestBody PetDto dto){
        PetEntity petEntity = petService.setPet(dto);
        return new ResponseEntity<>(petEntity, HttpStatus.CREATED);
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
