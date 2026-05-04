package com.br.volleyhub.domain.service;

import com.br.volleyhub.api.dto.scorekeeper.*;

import java.util.List;

public interface ScoreKeeperService {
    ConfiguracaoPartidaResponse criarOuAtualizarConfiguracao(Long partidaId, ConfiguracaoPartidaRequest request);
    ConfiguracaoPartidaResponse buscarConfiguracao(Long partidaId);

    PlacarSetResponse registrarSet(Long partidaId, PlacarSetRequest request);
    List<PlacarSetResponse> listarSets(Long partidaId);

    EventoPontoResponse registrarEvento(Long partidaId, EventoPontoRequest request);
    List<EventoPontoResponse> listarEventos(Long partidaId);
}
