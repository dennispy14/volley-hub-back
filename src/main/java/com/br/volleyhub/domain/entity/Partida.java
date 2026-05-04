package com.br.volleyhub.domain.entity;

import com.br.volleyhub.domain.enums.FaseTorneio;
import com.br.volleyhub.domain.enums.StatusPartida;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "partidas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "torneio_id")
    private Torneio torneio;

    @ManyToOne
    @JoinColumn(name = "time_a_id")
    private Time timeA;

    @ManyToOne
    @JoinColumn(name = "time_b_id")
    private Time timeB;

    private Integer placarA;
    private Integer placarB;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private FaseTorneio fase;

    private String nomeGrupo;
    
    private Integer ordem;

    @Enumerated(EnumType.STRING)
    private StatusPartida status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsavel_id")
    private Usuario responsavel;

    @Column(name = "liga_id")
    private Long ligaId;
}
