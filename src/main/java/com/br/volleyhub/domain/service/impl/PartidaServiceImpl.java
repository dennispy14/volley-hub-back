package com.br.volleyhub.domain.service.impl;

import com.br.volleyhub.api.dto.partida.*;
import com.br.volleyhub.api.mapper.PartidaMapper;
import com.br.volleyhub.domain.entity.Usuario;
import com.br.volleyhub.domain.enums.StatusPartida;
import com.br.volleyhub.domain.repository.*;
import com.br.volleyhub.domain.service.PartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartidaServiceImpl implements PartidaService {

    private final PartidaRepository partidaRepository;
    private final TorneioRepository torneioRepository;
    private final TimeRepository timeRepository;
    private final UsuarioRepository usuarioRepository;
    private final PartidaMapper mapper;

    @Override
    public PartidaResponse criar(PartidaRequest req) {

        var torneio = torneioRepository.findById(req.tournamentId())
                .orElseThrow(() -> new RuntimeException("Torneio nÃ£o encontrado"));

        var timeA = timeRepository.findById(req.teamAId())
                .orElseThrow(() -> new RuntimeException("Time A nÃ£o encontrado"));

        var timeB = timeRepository.findById(req.teamBId())
                .orElseThrow(() -> new RuntimeException("Time B não encontrado"));

        Usuario responsavel = null;
        if (req.responsavelId() != null) {
            responsavel = usuarioRepository.findById(req.responsavelId())
                    .orElseThrow(() -> new RuntimeException("Usuário responsável não encontrado"));
        } else {
            throw new RuntimeException("ID do usuário responsável é obrigatório");
        }

        var partida = mapper.toEntity(req, torneio, timeA, timeB, responsavel);

        return mapper.toResponse(partidaRepository.save(partida));
    }

    @Override
    public List<PartidaResponse> listarPorTorneio(Long torneioId) {
        return partidaRepository.findByTorneioId(torneioId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public PartidaResponse buscarPorId(Long id) {
        return partidaRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Partida nÃ£o encontrada"));
    }

    @Override
    public PartidaResponse atualizarPlacar(Long id, Integer placarA, Integer placarB) {
        var partida = partidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partida nÃ£o encontrada"));

        partida.setPlacarA(placarA);
        partida.setPlacarB(placarB);
        partida.setStatus(StatusPartida.FINALIZADA);

        return mapper.toResponse(partidaRepository.save(partida));
    }

    @Override
    public PartidaResponse atualizarStatus(Long id, StatusPartida novoStatus) {
        var partida = partidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partida nÃ£o encontrada"));

        if (!isValidTransition(partida.getStatus(), novoStatus)) {
            throw new IllegalStateException(
                    "TransiÃ§Ã£o de status invÃ¡lida: " + partida.getStatus() + " -> " + novoStatus);
        }

        partida.setStatus(novoStatus);
        partidaRepository.save(partida);
        return mapper.toResponse(partida);
    }

    private boolean isValidTransition(StatusPartida atual, StatusPartida novoStatus) {
        if (atual == null)
            return true;

        switch (atual) {
            case AGENDADA:
                return novoStatus == StatusPartida.EM_ANDAMENTO || novoStatus == StatusPartida.CANCELADA;
            case EM_ANDAMENTO:
                return novoStatus == StatusPartida.FINALIZADA || novoStatus == StatusPartida.CANCELADA;
            case FINALIZADA:
                return false;
            case CANCELADA:
                return false;
            default:
                return false;
        }
    }

    @Override
    public void reordenar(List<Long> partidaIds) {
        for (int i = 0; i < partidaIds.size(); i++) {
            Long partId = partidaIds.get(i);
            final int ordem = i;

            partidaRepository.findById(partId).ifPresent(p -> {
                p.setOrdem(ordem);
                partidaRepository.save(p);
            });
        }
    }
}