package com.dquinones.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Data Transfer Objet for Song
 *
 * This class is used to transfer song data between processes.
 * Contains Lombok annotations and Builder pattern.
 *
 */

@Data
@Builder
public class SongDto {

    private String name;
    private List<Long> authors;

}
