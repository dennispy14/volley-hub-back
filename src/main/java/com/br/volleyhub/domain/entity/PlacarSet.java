package com.br.volleyhub.domain.entity;

import com.br.volleyhub.domain.enums.Equipe;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "placares_set")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlacarSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partida_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Partida partida;

    private Integer setNumber;
    private Integer scoreA;
    private Integer scoreB;
    private Boolean isFinished;

    @Enumerated(EnumType.STRING)
    private Equipe winner;
}
