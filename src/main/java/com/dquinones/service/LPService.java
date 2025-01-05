package com.dquinones.service;

import com.dquinones.dto.LPDto;
import com.dquinones.dto.SongDto;
import com.dquinones.entities.Artist;
import com.dquinones.entities.Author;
import com.dquinones.entities.LP;
import com.dquinones.entities.Song;
import com.dquinones.repository.IArtistRepository;
import com.dquinones.repository.IAuthorRepository;
import com.dquinones.repository.ILPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Service class for managing LP records.
 */

@Service
public class LPService {

    @Autowired
    IArtistRepository artistRepository;

    @Autowired
    IAuthorRepository authorRepository;

    @Autowired
    ILPRepository lpRespository;


    /**
     * Method to create a new LP. Check if the artist and authors in the songs exist before proceeding to save the record in the database.
     * @param lpDto Data transfer Object LP details.
     * @return create LP entity.
     * @throws ResponseStatusException if artists or author not founf.
     */
    public LP crearLp(LPDto lpDto){
        LP lp = new LP();
        List<Song> songList = new ArrayList<>();

        Artist artistExisteix = artistRepository.findById(lpDto.getArtistId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found"));

        for(SongDto song : lpDto.getSongs()) {

            List<Author> authors = new ArrayList<>();
            Song song1 = new Song();

            for(Long idAuthor : song.getAuthors()) {
                Author author = authorRepository.findById(idAuthor).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));
                authors.add(author);
            }

            song1.setLp(lp);
            song1.setName(song.getName());
            song1.setAuthors(authors);

            songList.add(song1);
        }

        lp.setName(lpDto.getName());
        lp.setDescription(lpDto.getDescription());
        lp.setArtist(artistExisteix);
        lp.setSongs(songList);

        return lpRespository.save(lp);
    }

    /**
     * Obtain all LP
     * @return List of all LP entities.
     */
    public List<LP> findAll(){
        return lpRespository.findAll();
    }

    /**
     * Obtain an LPs by artist name.
     * @param nameArtist Name of artist
     * @return List of LP entities associated with the given artist name.
     * @throws ResponseStatusException if the artist not found.
     */
    public List<LP> findByArtistName(String nameArtist) {
        Artist artist = artistRepository.findByNameIgnoreCase(nameArtist).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found"));
        return lpRespository.findByArtist(artist);
    }

    /**
     * Update an existing LP. We only update the LP fields (Name, Descripction and Artist).
     * @param id of the LP to update.
     * @param lp Data transfer objet contains updated lp detail.
     * @return The update LP entity.
     * @throws ResponseStatusException if the lp or artist not found.
     */
    public LP update(Long id, LPDto lp){

        AtomicReference<LP> lpUpdate = new AtomicReference<>();

        lpRespository.findById(id).ifPresentOrElse(p -> {

            lpUpdate.set(p);
            Artist a = artistRepository.findById(lp.getArtistId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found"));
            lpUpdate.get().setArtist(a);
            lpUpdate.get().setName(lp.getName());
            lpUpdate.get().setDescription(lp.getDescription());

            lpRespository.save(lpUpdate.get());

        }, () -> {throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "LP not found for update");});

        return lpUpdate.get();
    }

    /**
     * Delete an LP and its songs by their Id
     * @param id of the LP to delete.
     * @return ResponseEntity indicating the result operation.
     * @throws ResponseStatusException if LP not exists.
     */
    public ResponseEntity<?> delete(Long id) {
        lpRespository.delete(lpRespository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LP not found") ));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
