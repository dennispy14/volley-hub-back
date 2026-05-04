package com.br.volleyhub.domain.entity;

import com.br.volleyhub.domain.enums.Equipe;
import com.br.volleyhub.domain.enums.MotivoPonto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "eventos_ponto")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventoPonto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partida_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Partida partida;

    private Integer setNumber;

    @Enumerated(EnumType.STRING)
    private Equipe team;

    @Enumerated(EnumType.STRING)
    private MotivoPonto reason;

    private String playerName;
    private String observation;

    private Integer scoreAAtMoment;
    private Integer scoreBAtMoment;

    private LocalDateTime timestamp;
    private Boolean undone;
}
