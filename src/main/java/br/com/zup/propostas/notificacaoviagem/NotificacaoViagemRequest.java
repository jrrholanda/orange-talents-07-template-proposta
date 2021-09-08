package br.com.zup.propostas.notificacaoviagem;

import br.com.zup.propostas.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class NotificacaoViagemRequest {

    @NotBlank
    private String destino;
    @NotNull
    private LocalDateTime dataRetorno;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NotificacaoViagemRequest(String destino, LocalDateTime dataRetorno) {
        this.destino = destino;
        this.dataRetorno = dataRetorno;
    }

    public NotificacaoViagem toModel(String ipCliente, String userAgent, Cartao cartao){
        return new NotificacaoViagem(this.destino, this.dataRetorno, cartao, ipCliente, userAgent);
    }
}
