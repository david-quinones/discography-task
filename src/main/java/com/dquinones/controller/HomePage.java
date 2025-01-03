package com.dquinones.controller;

import com.dquinones.dto.HomePageLPDto;
import com.dquinones.entities.Author;
import com.dquinones.service.HomePageService;
import com.dquinones.service.LPService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController()
@RequestMapping("/api/home")
@Tag(name="Home Page", description = "Controller that returns the data from the home page.")
public class HomePage {

    private final HomePageService homePageService;

    @GetMapping
    @Operation(
            summary = "Obtains formatted data", // resum
            description = "Returns a formatted list of the different LPs, along with the artist, number of songs and a list of all the authors involved." // descripció
    )
    public ResponseEntity<?> getHomePage(){
        return new ResponseEntity<>(homePageService.getAllHomePage(), HttpStatus.OK);
    }
}


