package com.br.volleyhub.domain.service.impl;

import com.br.volleyhub.api.dto.time.TimeRequest;
import com.br.volleyhub.api.dto.time.TimeResponse;
import com.br.volleyhub.api.mapper.TimeMapper;
import com.br.volleyhub.domain.entity.Jogador;
import com.br.volleyhub.domain.entity.Time;
import com.br.volleyhub.domain.repository.JogadorRepository;
import com.br.volleyhub.domain.repository.TimeRepository;
import com.br.volleyhub.domain.service.TimeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TimeServiceImpl implements TimeService {

    private final TimeRepository repository;
    private final JogadorRepository jogadorRepository;
    private final TimeMapper mapper;

    @Override
    @Transactional
    public TimeResponse criar(TimeRequest request) {
        Time entity = mapper.toEntity(request);
        Time salvo = repository.save(entity);
        syncJogadores(salvo, request.playerIds());
        return mapper.toResponse(repository.save(salvo));
    }

    @Override
    public List<TimeResponse> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public TimeResponse buscarPorId(Long id) {
        Time entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Time nao encontrado"));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional
    public TimeResponse atualizar(Long id, TimeRequest request) {
        Time entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Time nao encontrado"));

        mapper.updateEntityFromRequest(request, entity);
        syncJogadores(entity, request.playerIds());
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private void syncJogadores(Time time, List<Long> playerIds) {
        if (playerIds == null) {
            return;
        }

        List<Long> ids = playerIds.stream()
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        List<Jogador> jogadoresSelecionados = jogadorRepository.findAllById(ids);
        if (jogadoresSelecionados.size() != ids.size()) {
            throw new RuntimeException("Um ou mais jogadores nao foram encontrados");
        }

        List<Jogador> jogadoresAtuais = time.getJogadores() == null
                ? List.of()
                : time.getJogadores();

        jogadoresAtuais.stream()
                .filter(jogador -> !ids.contains(jogador.getId()))
                .forEach(jogador -> jogador.setTime(null));

        jogadoresSelecionados.forEach(jogador -> jogador.setTime(time));

        jogadorRepository.saveAll(jogadoresAtuais);
        jogadorRepository.saveAll(jogadoresSelecionados);
        time.setJogadores(jogadoresSelecionados);
    }
}
