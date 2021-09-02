package br.com.zup.propostas.proposta;

public class StatusPropostaResponse {

    private StatusProposta status;

    public StatusPropostaResponse(Proposta proposta) {
        this.status = proposta.getStatus();
    }

    public StatusProposta getStatus() {
        return status;
    }
}
