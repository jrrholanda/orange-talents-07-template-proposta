package br.com.zup.propostas.cartao.integracao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "${external.services.url.contas}", name = "contas")
public interface CartaoClient {

    @RequestMapping(method = RequestMethod.POST, path ="/cartoes")
    CartaoResponse buscaCartao(CartaoRequest cartaoRequest);
}
