package com.br.volleyhub.domain.service.impl;

import com.br.volleyhub.api.dto.scorekeeper.*;
import com.br.volleyhub.api.mapper.ScoreKeeperMapper;
import com.br.volleyhub.domain.entity.*;
import com.br.volleyhub.domain.repository.*;
import com.br.volleyhub.domain.service.ScoreKeeperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreKeeperServiceImpl implements ScoreKeeperService {

    private final ConfiguracaoPartidaRepository configRepository;
    private final PlacarSetRepository setRepository;
    private final EventoPontoRepository eventoRepository;
    private final PartidaRepository partidaRepository;
    private final ScoreKeeperMapper mapper;

    private Partida getPartida(Long partidaId) {
        return partidaRepository.findById(partidaId)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada com ID " + partidaId));
    }

    @Override
    @Transactional
    public ConfiguracaoPartidaResponse criarOuAtualizarConfiguracao(Long partidaId, ConfiguracaoPartidaRequest request) {
        Partida partida = getPartida(partidaId);
        ConfiguracaoPartida config = configRepository.findByPartidaId(partidaId)
                .orElse(new ConfiguracaoPartida());

        config.setPartida(partida);
        config.setMaxSets(request.maxSets());
        config.setPointsPerSet(request.pointsPerSet());
        config.setTieBreakPoints(request.tieBreakPoints());
        config.setMinDifference(request.minDifference());
        config.setMatchType(request.matchType());

        config = configRepository.save(config);
        return mapper.toResponse(config);
    }

    @Override
    public ConfiguracaoPartidaResponse buscarConfiguracao(Long partidaId) {
        return configRepository.findByPartidaId(partidaId)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Configuração não encontrada para a partida " + partidaId));
    }

    @Override
    @Transactional
    public PlacarSetResponse registrarSet(Long partidaId, PlacarSetRequest request) {
        Partida partida = getPartida(partidaId);
        PlacarSet placar = setRepository.findByPartidaIdAndSetNumber(partidaId, request.setNumber())
                .orElse(new PlacarSet());

        placar.setPartida(partida);
        placar.setSetNumber(request.setNumber());
        placar.setScoreA(request.scoreA());
        placar.setScoreB(request.scoreB());
        placar.setIsFinished(request.isFinished());
        placar.setWinner(request.winner());

        placar = setRepository.save(placar);
        return mapper.toResponse(placar);
    }

    @Override
    public List<PlacarSetResponse> listarSets(Long partidaId) {
        return setRepository.findByPartidaId(partidaId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public EventoPontoResponse registrarEvento(Long partidaId, EventoPontoRequest request) {
        Partida partida = getPartida(partidaId);
        EventoPonto evento = mapper.toEntity(request, partida);
        evento = eventoRepository.save(evento);
        return mapper.toResponse(evento);
    }

    @Override
    public List<EventoPontoResponse> listarEventos(Long partidaId) {
        return eventoRepository.findByPartidaIdOrderByTimestampAsc(partidaId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
