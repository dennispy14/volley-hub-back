package com.br.volleyhub.api.dto.partida;

import com.br.volleyhub.domain.enums.FaseTorneio;
import java.time.LocalDateTime;

public record PartidaRequest(
                Long tournamentId,
                Long teamAId,
                Long teamBId,
                LocalDateTime dateTime,
                FaseTorneio phase,
                String nomeGrupo,
                Integer ordem,
                Long responsavelId,
                Long ligaId) {
}
