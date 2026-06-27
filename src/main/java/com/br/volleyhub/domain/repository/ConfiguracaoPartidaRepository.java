package com.br.volleyhub.domain.repository;

import com.br.volleyhub.domain.entity.ConfiguracaoPartida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfiguracaoPartidaRepository extends JpaRepository<ConfiguracaoPartida, Long> {
    Optional<ConfiguracaoPartida> findByPartidaId(Long partidaId);
}
