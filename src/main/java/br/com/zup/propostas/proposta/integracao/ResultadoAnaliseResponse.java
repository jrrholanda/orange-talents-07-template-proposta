package br.com.zup.propostas.proposta.integracao;

import br.com.zup.propostas.proposta.StatusProposta;
import br.com.zup.propostas.proposta.integracao.SolicitacaoAnaliseRequest;

public class ResultadoAnaliseResponse {

    private String documento;
    private String nome;
    private String resultadoSolicitacao;
    private String idProposta;

    public ResultadoAnaliseResponse(SolicitacaoAnaliseRequest request, String resultado) {
        this.documento = request.getDocumento();
        this.nome = request.getNome();
        this.resultadoSolicitacao = resultado;
        this.idProposta = request.getIdProposta();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public StatusProposta getStatus(){
        if(resultadoSolicitacao.toUpperCase().equals("SEM_RESTRICAO"))
            return StatusProposta.ELEGIVEL;
        return StatusProposta.NAO_ELEGIVEL;
    }
}
