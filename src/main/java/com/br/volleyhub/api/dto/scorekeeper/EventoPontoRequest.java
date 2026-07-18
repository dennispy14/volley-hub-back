package com.br.volleyhub.api.dto.scorekeeper;

import com.br.volleyhub.domain.enums.Equipe;
import com.br.volleyhub.domain.enums.MotivoPonto;

import java.time.LocalDateTime;

public record EventoPontoRequest(
        Integer setNumber,
        Equipe team,
        Equipe actorTeam,
        MotivoPonto reason,
        String playerName,
        String observation,
        Integer scoreAAtMoment,
        Integer scoreBAtMoment,
        LocalDateTime timestamp,
        Boolean undone
) {
}
