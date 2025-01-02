package com.dquinones.repository;

import com.dquinones.entities.Artist;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByNameIgnoreCase(@NotBlank String name);
}
