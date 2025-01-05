package com.dquinones.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Data Transfer Objet for LP
 *
 * This class is used to transfer LP data between processes.
 * Contains Lombok annotations and Builder pattern.
 *
 */

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
