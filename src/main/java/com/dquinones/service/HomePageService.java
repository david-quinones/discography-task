package com.dquinones.service;

import com.dquinones.dto.HomePageLPDto;
import com.dquinones.entities.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing Home Page.
 */
@Service
public class HomePageService {

    private LPService lpService;

    public HomePageService(LPService lpService) {
        this.lpService = lpService;
    }

    /**
     * Method that obtains all the LPs in the system, goes through each LP to extract, id, name, artistName, the number of songs
     * and goes through the different songs to extract the authors, making a distinct and concatenating them with ','
     *
     * @return Data transfer objetc HomePage (LP)
     */
    public List<HomePageLPDto> getAllHomePage(){

        return lpService.findAll().stream().map(lp -> {
            return HomePageLPDto.builder()
                    .id(lp.getId())
                    .lpName(lp.getName())
                    .artistName(lp.getArtist().getName())
                    .numSongs(lp.getSongs().size())
                    .authorsName(lp.getSongs().stream()
                            .flatMap(song -> song.getAuthors().stream().map(Author::getName))
                            .distinct()
                            .collect(Collectors.joining(", "))
                    )
                    .build();
        }).toList();
    }

}
