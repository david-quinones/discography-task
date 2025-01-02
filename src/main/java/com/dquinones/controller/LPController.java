package com.dquinones.controller;

import com.dquinones.dto.LPDto;
import com.dquinones.entities.Artist;
import com.dquinones.service.ArtistService;
import com.dquinones.service.LPService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lp")
public class LPController {

    @Autowired
    private LPService lpService;

    // POST
    @PostMapping()
    public ResponseEntity<?> create (@Valid @RequestBody LPDto lpDto) {

        return new ResponseEntity<>(lpService.crearLp(lpDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(lpService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/artist")
    public ResponseEntity<?> findByArtistName(@RequestParam(required = true) final String nameArtist){
        return new ResponseEntity<>(lpService.findByArtistName(nameArtist), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody LPDto lpDto){
        return new ResponseEntity<>(lpService.update(id, lpDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        lpService.delete(id);
        return ResponseEntity.noContent().build();
    }




}
