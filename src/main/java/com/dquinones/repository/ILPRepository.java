package com.dquinones.repository;

import com.dquinones.entities.LP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILPRepository extends JpaRepository<LP, Long> {
}
