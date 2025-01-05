package com.dquinones.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entity class representing a Song.
 * The class is annotated with Lombok, Jackson and JPA.
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(targetEntity = LP.class)
    @JsonBackReference
    private LP lp;

    @ManyToMany(targetEntity = Author.class,fetch = FetchType.LAZY)
    private List<Author> authors;
}
