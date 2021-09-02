package br.com.zup.propostas.cartao.integracao;

import br.com.zup.propostas.proposta.Proposta;

public class CartaoRequest {

    private String documento;
    private String nome;
    private String idProposta;

    @Deprecated
    public CartaoRequest() {
    }

    public CartaoRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId().toString();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

}
