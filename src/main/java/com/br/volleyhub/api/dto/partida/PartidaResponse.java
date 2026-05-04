package com.br.volleyhub.api.dto.partida;

import com.br.volleyhub.domain.enums.FaseTorneio;
import com.br.volleyhub.domain.enums.StatusPartida;

import java.time.LocalDateTime;

public record PartidaResponse(
                Long id,
                Long tournamentId,
                Long teamAId,
                Long teamBId,
                Integer scoreA,
                Integer scoreB,
                LocalDateTime dateTime,
                FaseTorneio phase,
                String phaseDescription,
                String nomeGrupo,
                Integer ordem,
                StatusPartida status,
                Long responsavelId,
                Long ligaId) {
}
