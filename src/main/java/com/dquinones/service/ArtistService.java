package com.dquinones.service;

import com.dquinones.dto.ArtistDto;
import com.dquinones.entities.Artist;
import com.dquinones.repository.IArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * Service class for managing artist records.
 */

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final IArtistRepository artistRepository;

    /**
     * Create a new Artist
     * @param artist Artists details
     * @return Artist entity create entity.
     */
    public Artist create(Artist artist) {
        return artistRepository.save(artist);
    }

    /**
     * Obtain an Artit by Id
     * @param id of artists find.
     * @return Artist data transfer objet
     * @throws ResponseStatusException if the artist not found.
     */
    public ArtistDto findById(Long id){
        return artistRepository.findById(id)
                .map(artist -> {
                    return createArtistDto(artist);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found."));
    }

    /**
     * Obtain all artists entities
     * @return List of all the Artists, in ArtistDto format
     */
    public List<ArtistDto> findAll(){
        return artistRepository.findAll()
                .stream()
                .map(artist -> {
                    return createArtistDto(artist);
                }).collect(Collectors.toList());
    }

    /**
     * Update an existing Artist.
     * @param id of the Artits to update.
     * @param artist an Artist enties.
     * @return the update Artist on format dto.
     * @throws ResponseStatusException if the artists not exists
     */
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

    /**
     * Delete an Artists by their Id
     * @param id of the artist to delete.
     * @return ResponseEntity indicating the result of operation.
     * @throws ResponseStatusException if artist not exists.
     */
    public ResponseEntity<?> delete(Long id) {
        artistRepository.delete(artistRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found.")));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * Method to create and return an ArtistaDto
     * @param artist Object an Artist entities
     * @return ArtistDto created based on Artist
     */
    private ArtistDto createArtistDto(Artist artist){
        return ArtistDto.builder()
                .id(artist.getId())
                .name(artist.getName())
                .description(artist.getDescription())
                .numberLp(artist.getLps().size())
                .build();
    }
}
