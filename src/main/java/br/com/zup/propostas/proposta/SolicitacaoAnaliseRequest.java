package br.com.zup.propostas.proposta;

public class SolicitacaoAnaliseRequest {

    private String documento;
    private String nome;
    private String idProposta;

    @Deprecated
    public SolicitacaoAnaliseRequest() {
    }

    public SolicitacaoAnaliseRequest(Proposta proposta) {
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
