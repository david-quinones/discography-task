package com.dquinones.service;

import com.dquinones.dto.HomePageLPDto;
import com.dquinones.entities.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomePageService {

    private LPService lpService;

    public HomePageService(LPService lpService) {
        this.lpService = lpService;
    }

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
