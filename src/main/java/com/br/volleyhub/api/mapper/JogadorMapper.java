package com.br.volleyhub.api.mapper;

import com.br.volleyhub.api.dto.jogador.JogadorRequest;
import com.br.volleyhub.api.dto.jogador.JogadorResponse;
import com.br.volleyhub.domain.entity.Jogador;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface JogadorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "apelido", ignore = true)
    @Mapping(target = "cidade", ignore = true)
    @Mapping(target = "nome", source = "name")
    @Mapping(target = "idade", source = "age")
    @Mapping(target = "genero", source = "gender")
    @Mapping(target = "posicao", source = "position")
    @Mapping(target = "time", ignore = true)
    @Mapping(target = "imagemUrl", source = "imageUrl")
    Jogador toEntity(JogadorRequest request);

    @Mapping(target = "name", source = "nome")
    @Mapping(target = "age", source = "idade")
    @Mapping(target = "gender", source = "genero")
    @Mapping(target = "position", source = "posicao")
    @Mapping(target = "team", source = "time.nome")
    @Mapping(target = "imageUrl", source = "imagemUrl")
    JogadorResponse toResponse(Jogador entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "apelido", ignore = true)
    @Mapping(target = "cidade", ignore = true)
    @Mapping(target = "nome", source = "name")
    @Mapping(target = "idade", source = "age")
    @Mapping(target = "genero", source = "gender")
    @Mapping(target = "posicao", source = "position")
    @Mapping(target = "time", ignore = true)
    @Mapping(target = "imagemUrl", source = "imageUrl")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(JogadorRequest request, @MappingTarget Jogador entity);
}
