package com.br.volleyhub.domain.entity;

import com.br.volleyhub.domain.enums.TipoPartida;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "configuracoes_partida")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracaoPartida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partida_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Partida partida;

    private Integer maxSets;
    private Integer pointsPerSet;
    private Integer tieBreakPoints;
    private Integer minDifference;

    @Enumerated(EnumType.STRING)
    private TipoPartida matchType;
}
