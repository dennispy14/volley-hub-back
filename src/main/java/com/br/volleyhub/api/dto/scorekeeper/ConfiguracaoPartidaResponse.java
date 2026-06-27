package com.br.volleyhub.api.dto.scorekeeper;

import com.br.volleyhub.domain.enums.TipoPartida;

public record ConfiguracaoPartidaResponse(
        Long id,
        Long partidaId,
        Integer maxSets,
        Integer pointsPerSet,
        Integer tieBreakPoints,
        Integer minDifference,
        TipoPartida matchType
) {
}
