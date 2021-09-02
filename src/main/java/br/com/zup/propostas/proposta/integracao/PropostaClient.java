package br.com.zup.propostas.proposta.integracao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${external.services.url.analise}", name = "analise")
public interface PropostaClient {

    @PostMapping("/solicitacao")
    ResultadoAnaliseResponse enviaParaAnalise(SolicitacaoAnaliseRequest request);
}
