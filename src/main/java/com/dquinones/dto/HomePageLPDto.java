package com.dquinones.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HomePageLPDto {

    private Long id;
    private String lpName;
    private String artistName;
    private Integer numSongs;
    private String authorsName;
}
