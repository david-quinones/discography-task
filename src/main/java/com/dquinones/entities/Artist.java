package com.dquinones.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @OneToMany(targetEntity = LP.class, fetch = FetchType.LAZY, mappedBy = "artist")
    private List<LP> lps;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LP> getLps() {
        return lps;
    }

    public void setLps(List<LP> lps) {
        this.lps = lps;
    }
}
