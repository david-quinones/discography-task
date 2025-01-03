package com.dquinones.controller;

import com.dquinones.dto.HomePageLPDto;
import com.dquinones.entities.Author;
import com.dquinones.service.HomePageService;
import com.dquinones.service.LPService;
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
public class HomePage {

    private final HomePageService homePageService;

    @GetMapping
    public ResponseEntity<?> getHomePage(){
        return new ResponseEntity<>(homePageService.getAllHomePage(), HttpStatus.OK);
    }
}


