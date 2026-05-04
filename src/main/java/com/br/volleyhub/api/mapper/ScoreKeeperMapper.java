package com.br.volleyhub.api.mapper;

import com.br.volleyhub.api.dto.scorekeeper.*;
import com.br.volleyhub.domain.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScoreKeeperMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "partida", source = "partida")
    ConfiguracaoPartida toEntity(ConfiguracaoPartidaRequest request, Partida partida);

    @Mapping(target = "partidaId", source = "partida.id")
    ConfiguracaoPartidaResponse toResponse(ConfiguracaoPartida entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "partida", source = "partida")
    @Mapping(target = "number", ignore = true)
    PlacarSet toEntity(PlacarSetRequest request, Partida partida);

    @Mapping(target = "partidaId", source = "partida.id")
    PlacarSetResponse toResponse(PlacarSet entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "partida", source = "partida")
    @Mapping(target = "number", ignore = true)
    EventoPonto toEntity(EventoPontoRequest request, Partida partida);

    @Mapping(target = "partidaId", source = "partida.id")
    EventoPontoResponse toResponse(EventoPonto entity);
}
