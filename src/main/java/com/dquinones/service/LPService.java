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

@Service
public class LPService {

    @Autowired
    IArtistRepository artistRepository;

    @Autowired
    IAuthorRepository authorRepository;

    @Autowired
    ILPRepository lpRespository;


    // Create
    public LP crearLp(LPDto lpDto){
        LP lp = new LP();
        List<Song> songList = new ArrayList<>();

        // Comprovar que l'artista existeix
        Artist artistExisteix = artistRepository.findById(lpDto.getArtistId()).orElseThrow(() -> new RuntimeException("Artist not found"));

        // Recorrem cada canço i cada autor, comprovant que els autors existeixen
        for(SongDto song : lpDto.getSongs()) {

            List<Author> authors = new ArrayList<>();
            Song song1 = new Song();

            for(Long idAuthor : song.getAuthors()) {
                Author author = authorRepository.findById(idAuthor).orElseThrow(() -> new RuntimeException("Artist not found"));
                authors.add(author);
            }

            // Complimentem l'objecte Song
            song1.setLp(lp);
            song1.setName(song.getName());
            song1.setAuthors(authors);

            // afegim la canço al llista de cançons
            songList.add(song1);

        }

        // creem i guardem l'objecte LP
        lp.setName(lpDto.getName());
        lp.setDescription(lpDto.getDescription());
        lp.setArtist(artistExisteix);
        lp.setSongs(songList);

        return lpRespository.save(lp);
    }

    // Read
    public List<LP> findAll(){
        return lpRespository.findAll();
    }

    // Read
    public List<LP> findByArtistName(String nameArtist) {
        Artist artist = artistRepository.findByNameIgnoreCase(nameArtist).orElseThrow(() -> new RuntimeException("Artist not found"));
        return lpRespository.findByArtist(artist);
    }

    // Update (name, description, artist)
    public LP update(Long id, LPDto lp){

        AtomicReference<LP> lpUpdate = new AtomicReference<>();

        lpRespository.findById(id).ifPresentOrElse(p -> {

            // assignem objecte
            lpUpdate.set(p);
            // comprovem si existeix l'artista
            Artist a = artistRepository.findById(lp.getArtistId()).orElseThrow(() -> new RuntimeException("Artist not found"));
            // actualizem l'objecte amb l'artista
            lpUpdate.get().setArtist(a);
            // actualitzem nom i descripcio
            lpUpdate.get().setName(lp.getName());
            lpUpdate.get().setDescription(lp.getDescription());

            // actualizatem objecte
            lpRespository.save(lpUpdate.get());

        }, () -> {throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "LP not found for update");});

        return lpUpdate.get();
    }

    // Delete
    public ResponseEntity<?> delete(Long id) {
        lpRespository.delete(lpRespository.findById(id).orElseThrow());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
