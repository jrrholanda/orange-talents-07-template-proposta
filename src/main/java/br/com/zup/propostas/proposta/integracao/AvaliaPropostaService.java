package br.com.zup.propostas.proposta.integracao;

import br.com.zup.propostas.proposta.Proposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Service
@Validated
public class AvaliaPropostaService {

    @Autowired
    private SolicitacaoAnaliseFinanceira analiseFinanceira;

    public ResultadoAnaliseResponse executa(@NotNull @Validated Proposta proposta) {
        SolicitacaoAnaliseRequest request = new SolicitacaoAnaliseRequest(proposta);
        String resultadoAvaliacao = analiseFinanceira.enviaParaAnalise(request);

        return new ResultadoAnaliseResponse(request, resultadoAvaliacao);
    }
}
