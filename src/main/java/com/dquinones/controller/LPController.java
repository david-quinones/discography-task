package com.dquinones.controller;

import com.dquinones.dto.LPDto;
import com.dquinones.entities.Artist;
import com.dquinones.service.ArtistService;
import com.dquinones.service.LPService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lp")
@Tag(name="LP", description = "Controller with different CRUD methods on the LP entity and its subclass song.")
public class LPController {

    @Autowired
    private LPService lpService;

    // POST
    @PostMapping()
    @Operation(
            summary = "Create a new LP",
            description = "Method for creating a new LP and its songs"
    )
    public ResponseEntity<?> create (@Valid @RequestBody LPDto lpDto) {

        return new ResponseEntity<>(lpService.crearLp(lpDto), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
            summary = "Get all LP",
            description = "Returns all LPs in the system in the object format of the same LP."
    )
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(lpService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/artist")
    @Operation(
            summary = "Obtain an artist's LPs by name",
            description = "Returns the LPs in the system of the artist that is passed as a parameter in the request."
    )
    public ResponseEntity<?> findByArtistName(@RequestParam(required = true) final String nameArtist){
        return new ResponseEntity<>(lpService.findByArtistName(nameArtist), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update LP information",
            description = "Method to modify an LP, being able to change the ArtistId, Name and Description."
    )
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody LPDto lpDto){
        return new ResponseEntity<>(lpService.update(id, lpDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete an LP",
            description = "Method to delete an LP and its associated songs, passing as path parameter its Id."
    )
    public ResponseEntity<?> delete(@PathVariable Long id){
        lpService.delete(id);
        return ResponseEntity.noContent().build();
    }




}
