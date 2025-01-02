package com.dquinones.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SongDto {

    private String name;
    private List<Long> authors;

}
