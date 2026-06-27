package com.br.volleyhub.domain.repository;

import com.br.volleyhub.domain.entity.PlacarSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlacarSetRepository extends JpaRepository<PlacarSet, Long> {
    List<PlacarSet> findByPartidaId(Long partidaId);
    Optional<PlacarSet> findByPartidaIdAndSetNumber(Long partidaId, Integer setNumber);
}
