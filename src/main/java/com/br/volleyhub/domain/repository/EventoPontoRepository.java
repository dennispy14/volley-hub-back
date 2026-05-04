package com.br.volleyhub.domain.repository;

import com.br.volleyhub.domain.entity.EventoPonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoPontoRepository extends JpaRepository<EventoPonto, Long> {
    List<EventoPonto> findByPartidaIdOrderByTimestampAsc(Long partidaId);
}
