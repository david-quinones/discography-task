package com.dquinones.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LPDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private Long artistId;
    private List<SongDto> songs;

}
