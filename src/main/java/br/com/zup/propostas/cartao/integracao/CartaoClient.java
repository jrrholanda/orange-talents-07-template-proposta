package br.com.zup.propostas.cartao.integracao;

import br.com.zup.propostas.notificacaoviagem.integracao.AvisoViagemRequest;
import br.com.zup.propostas.notificacaoviagem.integracao.AvisoViagemResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "${external.services.url.contas}", name = "contas")
public interface CartaoClient {

    @RequestMapping(method = RequestMethod.POST, path ="/cartoes")
    CartaoResponse buscaCartao(CartaoRequest cartaoRequest);

    @RequestMapping(method = RequestMethod.POST, path ="/cartoes/{numeroCartao}/bloqueios")
    BloqueioCartaoResponse bloqueiaCartao(@PathVariable("numeroCartao") String numeroCartao, @RequestBody BloqueioCartaoRequest bloqueioRequest);

    @RequestMapping(method = RequestMethod.POST, path ="/cartoes/{numeroCartao}/avisos")
    AvisoViagemResponse notificacaoViagem(@PathVariable("numeroCartao") String numeroCartao, @RequestBody AvisoViagemRequest avisoViagemRequest);
}
