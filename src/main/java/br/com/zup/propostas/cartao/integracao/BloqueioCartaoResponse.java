package br.com.zup.propostas.cartao.integracao;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BloqueioCartaoResponse {

    private String resultado;

    public String getResultado() {
        return resultado;
    }
}
