package com.br.volleyhub.api.dto.scorekeeper;

import com.br.volleyhub.domain.enums.Equipe;

public record PlacarSetRequest(
        Integer setNumber,
        Integer scoreA,
        Integer scoreB,
        Boolean isFinished,
        Equipe winner
) {
}
