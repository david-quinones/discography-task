package com.dquinones.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entity class representing an LP.
 * The class is annotated with Lombok, Jackson and JPA.
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class LP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne(targetEntity = Artist.class)
    private Artist artist;

    @OneToMany(targetEntity = Song.class, fetch = FetchType.LAZY, mappedBy = "lp", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Song> songs;
}
