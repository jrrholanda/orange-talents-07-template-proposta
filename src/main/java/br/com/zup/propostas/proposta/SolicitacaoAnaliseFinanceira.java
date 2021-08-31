package br.com.zup.propostas.proposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "solicitacaoAnaliseFinanceira", url="${solicitacaoAnaliseFinanceiraClient.targetUrl}")
public interface SolicitacaoAnaliseFinanceira {

    @PostMapping("solicitacoes")
    public String enviaParaAnalise(SolicitacaoAnaliseRequest request);

}
