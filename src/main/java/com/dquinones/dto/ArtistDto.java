package com.dquinones.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Objet for Artist
 *
 * This class is used to transfer artist data between processes.
 * Contains Lombok annotations and Builder pattern.
 *
 */

@Data
@Builder
public class ArtistDto {

    private Long id;
    private String name;
    private String description;
    private int numberLp;

}
