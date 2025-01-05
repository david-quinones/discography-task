package com.dquinones.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Objet for HomePageLp
 *
 * This class is used to transfer HomePageLp data between processes.
 * Contains Lombok annotations and Builder pattern.
 *
 */

@Data
@Builder
public class HomePageLPDto {

    private Long id;
    private String lpName;
    private String artistName;
    private Integer numSongs;
    private String authorsName;
}
