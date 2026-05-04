package com.br.volleyhub.api.mapper;

import com.br.volleyhub.api.dto.partida.PartidaRequest;
import com.br.volleyhub.api.dto.partida.PartidaResponse;
import com.br.volleyhub.api.dto.partida.PartidaResultadoRequest;
import com.br.volleyhub.domain.entity.Partida;
import com.br.volleyhub.domain.entity.Time;
import com.br.volleyhub.domain.entity.Torneio;
import com.br.volleyhub.domain.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PartidaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "placarA", ignore = true)
    @Mapping(target = "placarB", ignore = true)
    @Mapping(target = "torneio", source = "torneio")
    @Mapping(target = "timeA", source = "timeA")
    @Mapping(target = "timeB", source = "timeB")
    @Mapping(target = "dataHora", source = "request.dateTime")
    @Mapping(target = "fase", source = "request.phase")
    @Mapping(target = "nomeGrupo", source = "request.nomeGrupo")
    @Mapping(target = "ordem", source = "request.ordem")
    @Mapping(target = "ligaId", source = "request.ligaId")
    @Mapping(target = "responsavel", source = "responsavel")
    Partida toEntity(PartidaRequest request, Torneio torneio, Time timeA, Time timeB, Usuario responsavel);

    @Mapping(target = "tournamentId", source = "torneio.id")
    @Mapping(target = "teamAId", source = "timeA.id")
    @Mapping(target = "teamBId", source = "timeB.id")
    @Mapping(target = "scoreA", source = "placarA")
    @Mapping(target = "scoreB", source = "placarB")
    @Mapping(target = "dateTime", source = "dataHora")
    @Mapping(target = "phase", source = "fase")
    @Mapping(target = "phaseDescription", source = "fase")
    @Mapping(target = "nomeGrupo", source = "nomeGrupo")
    @Mapping(target = "ordem", source = "ordem")
    @Mapping(target = "responsavelId", source = "responsavel.id")
    @Mapping(target = "ligaId", source = "ligaId")
    PartidaResponse toResponse(Partida partida);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "torneio", ignore = true)
    @Mapping(target = "timeA", ignore = true)
    @Mapping(target = "timeB", ignore = true)
    @Mapping(target = "dataHora", ignore = true)
    @Mapping(target = "fase", ignore = true)
    @Mapping(target = "nomeGrupo", ignore = true)
    @Mapping(target = "ordem", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "responsavel", ignore = true)
    @Mapping(target = "ligaId", ignore = true)
    @Mapping(target = "placarA", source = "scoreTeamA")
    @Mapping(target = "placarB", source = "scoreTeamB")
    void updateResultadoFromRequest(PartidaResultadoRequest request, @MappingTarget Partida entity);
}
