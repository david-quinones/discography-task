package com.dquinones.controller;

import com.dquinones.entities.Artist;
import com.dquinones.service.ArtistService;
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
