package com.dquinones.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne(targetEntity = Artist.class) // FK Artist
    private Artist artist;

    @OneToMany(targetEntity = Song.class, fetch = FetchType.LAZY, mappedBy = "lp")
    private List<Song> songs;
}
