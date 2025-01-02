package com.dquinones.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtistDto {

    private Long id;
    private String name;
    private String description;
    private int numberLp;

}
