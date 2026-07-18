package com.br.volleyhub.domain.enums;

import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;

class MotivoPontoTest {

    @Test
    void deveDisponibilizarNovasInfracoesEPreservarValoresLegados() {
        assertThat(EnumSet.allOf(MotivoPonto.class)).contains(
                MotivoPonto.ERRO_SAQUE,
                MotivoPonto.ERRO_ATAQUE,
                MotivoPonto.ERRO_RECEPCAO,
                MotivoPonto.DOIS_TOQUES,
                MotivoPonto.CONDUCAO,
                MotivoPonto.TOQUE_NA_REDE,
                MotivoPonto.ANTENA,
                MotivoPonto.QUATRO_TOQUES,
                MotivoPonto.LINHA,
                MotivoPonto.ATAQUE_IRREGULAR,
                MotivoPonto.DIGITAL,
                MotivoPonto.INVASAO_INTERFERENCIA,
                MotivoPonto.BLOQUEIO_SAQUE,
                MotivoPonto.ORDEM_SAQUE_INCORRETA,
                MotivoPonto.TOQUE_ESPACO_ADVERSARIO,
                MotivoPonto.ATAQUE_MAO_ABERTA,
                MotivoPonto.LEVANTAMENTO_IRREGULAR,
                MotivoPonto.SAQUE_FORCADO,
                MotivoPonto.OUTRO
        );
    }
}
