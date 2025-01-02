package com.dquinones.service;

import com.dquinones.dto.ArtistDto;
import com.dquinones.entities.Artist;
import com.dquinones.repository.IArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistService {

    @Autowired
    private IArtistRepository artistRepository;


    // Create
    public Artist create(Artist artist) {
        return artistRepository.save(artist);
    }

    // Read
    public ArtistDto findById(Long id){
        return artistRepository.findById(id)
                .map(artist -> {
                    return createArtistDto(artist);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found."));
    }

    public List<ArtistDto> findAll(){
        return artistRepository.findAll()
                .stream()
                .map(artist -> {
                    return createArtistDto(artist);
                }).collect(Collectors.toList());
    }

    // Update
    public ArtistDto update(Long id, Artist artist) {

        AtomicReference<Artist> artUpdate = new AtomicReference<>();

        artistRepository.findById(id).ifPresentOrElse(art-> {
            art.setName(artist.getName());
            art.setDescription(artist.getDescription());
            artistRepository.save(art);
            artUpdate.set(art);
        }, ()-> {throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found for your update");});

        return createArtistDto(artUpdate.get());

    }

    // Delete
    public ResponseEntity<?> delete(Long id) {
        artistRepository.delete(artistRepository.findById(id).orElseThrow());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private ArtistDto createArtistDto(Artist artist){

        return ArtistDto.builder()
                .id(artist.getId())
                .name(artist.getName())
                .description(artist.getDescription())
                .numberLp(artist.getLps().size())
                .build();
    }



}
