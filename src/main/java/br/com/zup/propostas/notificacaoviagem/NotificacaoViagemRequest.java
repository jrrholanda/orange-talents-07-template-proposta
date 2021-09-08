package br.com.zup.propostas.notificacaoviagem;

import br.com.zup.propostas.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class NotificacaoViagemRequest {

    @NotBlank
    private String destino;

    @NotNull @Future
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataRetorno;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NotificacaoViagemRequest(String destino, LocalDate dataRetorno) {
        this.destino = destino;
        this.dataRetorno = dataRetorno;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataRetorno() {
        return dataRetorno;
    }

    public NotificacaoViagem toModel(String ipCliente, String userAgent, Cartao cartao){
        return new NotificacaoViagem(this.destino, this.dataRetorno, cartao, ipCliente, userAgent);
    }
}
