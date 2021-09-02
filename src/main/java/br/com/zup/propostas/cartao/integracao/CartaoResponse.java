package br.com.zup.propostas.cartao.integracao;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.proposta.Proposta;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class  CartaoResponse {

    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private String idProposta;

    public String getNumeroCartao() {
        return id;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public String getTitular() {
        return titular;
    }

    public Cartao toModel(Proposta proposta) {
        return new Cartao(proposta, this.id, this.titular);
    }
}
