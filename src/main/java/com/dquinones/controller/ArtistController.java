package com.dquinones.controller;

import com.dquinones.entities.Artist;
import com.dquinones.service.ArtistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artist")
@Tag(name="Artist", description = "Controller with different CRUD methods on the Artitst entity.")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    // GET
    @GetMapping
    @Operation(
            summary = "Get all Artists",
            description = "Returns all Artists in the system in the object format of the same artist."
    )
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(artistService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a single Artist per Id",
            description = "Returns the information related to the Artist that is passed in the path."
    )
    public ResponseEntity<?> findById (@PathVariable Long id) {
        return new ResponseEntity<>(artistService.findById(id), HttpStatus.OK);
    }

    // POST
    @PostMapping()
    @Operation(
            summary = "Create a new Artist",
            description = "Method to create a new Artist in the system.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Artist.class)
                    )
            )
    )
    public ResponseEntity<?> create (@Valid @RequestBody Artist artist) {
        return new ResponseEntity<>(artistService.create(artist), HttpStatus.CREATED);
    }

    // PUT
    @PutMapping("/{id}")
    @Operation(
            summary = "Update Artist information",
            description = "Method to update name and description of an Artist by passing its Id through the path."
    )
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Artist a){
        return new ResponseEntity<>(artistService.update(id, a), HttpStatus.OK);
    }

    // DELET
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete an Artist",
            description = "Method to delete an Artist by passing his Id through the path."
    )
    public ResponseEntity<?> delete(@PathVariable Long id){
        artistService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
