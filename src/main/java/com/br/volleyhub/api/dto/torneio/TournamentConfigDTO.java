package com.br.volleyhub.api.dto.torneio;

import java.util.List;

public record TournamentConfigDTO(
    GameRulesDTO gameRules,
    List<TournamentPhaseDTO> phases,
    GroupConfigDTO groups,
    SeriesConfigDTO series
) {
    public record GameRulesDTO(
        Integer pointsPerSet,
        Integer pointsLastSet,
        Integer setsToWin,
        Boolean twoPointAdvantage,
        Boolean differentRulesForKnockout,
        Integer knockoutPointsPerSet,
        Integer knockoutSetsToWin,
        Integer estimatedMatchMinutes,
        Boolean thirdPlaceMatch
    ) {}

    public record TournamentPhaseDTO(
        String id,
        String name,
        String type,
        Integer order,
        Integer advancingTeams
    ) {}

    public record GroupConfigDTO(
        Integer numberOfGroups,
        Integer totalTeams,
        Integer teamsPerGroup,
        String matchFormat
    ) {}

    public record SeriesConfigDTO(
        Boolean enabled,
        String gold,
        String silver,
        String bronze,
        Integer numberOfSeries,
        Integer teamsPerSeries,
        String matchFormat
    ) {}
}
