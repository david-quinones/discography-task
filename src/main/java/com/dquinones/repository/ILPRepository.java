package com.dquinones.repository;

import com.dquinones.entities.Artist;
import com.dquinones.entities.LP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILPRepository extends JpaRepository<LP, Long> {
    List<LP> findByArtist(Artist artist);
}
