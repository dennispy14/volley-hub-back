package com.br.volleyhub.api.controller;

import com.br.volleyhub.api.dto.partida.PartidaResponse;
import com.br.volleyhub.api.dto.scorekeeper.*;
import com.br.volleyhub.domain.service.PartidaService;
import com.br.volleyhub.domain.service.ScoreKeeperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidas/{partidaId}")
@RequiredArgsConstructor
public class ScoreKeeperController {

    private final ScoreKeeperService scoreKeeperService;
    private final PartidaService partidaService;

    @GetMapping
    public ResponseEntity<PartidaResponse> buscarPartida(@PathVariable Long partidaId) {
        return ResponseEntity.ok(partidaService.buscarPorId(partidaId));
    }

    @PutMapping("/config")
    public ResponseEntity<ConfiguracaoPartidaResponse> criarOuAtualizarConfiguracao(
            @PathVariable Long partidaId,
            @RequestBody ConfiguracaoPartidaRequest request) {
        return ResponseEntity.ok(scoreKeeperService.criarOuAtualizarConfiguracao(partidaId, request));
    }

    @GetMapping("/config")
    public ResponseEntity<ConfiguracaoPartidaResponse> buscarConfiguracao(@PathVariable Long partidaId) {
        return ResponseEntity.ok(scoreKeeperService.buscarConfiguracao(partidaId));
    }

    @PostMapping("/sets")
    public ResponseEntity<PlacarSetResponse> registrarSet(
            @PathVariable Long partidaId,
            @RequestBody PlacarSetRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scoreKeeperService.registrarSet(partidaId, request));
    }

    @GetMapping("/sets")
    public ResponseEntity<List<PlacarSetResponse>> listarSets(@PathVariable Long partidaId) {
        return ResponseEntity.ok(scoreKeeperService.listarSets(partidaId));
    }

    @PostMapping("/eventos")
    public ResponseEntity<EventoPontoResponse> registrarEvento(
            @PathVariable Long partidaId,
            @RequestBody EventoPontoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scoreKeeperService.registrarEvento(partidaId, request));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<EventoPontoResponse>> listarEventos(@PathVariable Long partidaId) {
        return ResponseEntity.ok(scoreKeeperService.listarEventos(partidaId));
    }
}
