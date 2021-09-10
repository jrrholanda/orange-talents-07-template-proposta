package br.com.zup.propostas.carteira;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class InclusaoCarteiraResponse {

    private String resultado;
    private String id;

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
