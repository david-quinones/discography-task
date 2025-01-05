package com.dquinones.entities;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

/**
 * Entity class representing an Artist.
 * The class is annotated with Lombok, Jackson, Validations and JPA.
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
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @OneToMany(targetEntity = LP.class, fetch = FetchType.LAZY, mappedBy = "artist")

    @JsonIgnore
    private List<LP> lps;

}
