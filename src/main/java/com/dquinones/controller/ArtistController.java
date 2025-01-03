package com.dquinones.controller;

import com.dquinones.entities.Artist;
import com.dquinones.service.ArtistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    // GET
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(artistService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById (@PathVariable Long id) {
        return new ResponseEntity<>(artistService.findById(id), HttpStatus.OK);
    }

    // POST
    @PostMapping()
    @Operation(
            summary = "Crear un nou Artista",
            description = "Json a enviar per crear un nou artista",
            tags = {"artist, coditramuntana"},
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
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Artist a){
        return new ResponseEntity<>(artistService.update(id, a), HttpStatus.OK);
    }

    // DELET
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        artistService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
