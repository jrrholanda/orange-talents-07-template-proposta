package br.com.zup.propostas.proposta.integracao;

import br.com.zup.propostas.proposta.StatusProposta;

public class ResultadoAnaliseResponse {

    private String documento;
    private String nome;
    private String resultadoSolicitacao;
    private String idProposta;

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
