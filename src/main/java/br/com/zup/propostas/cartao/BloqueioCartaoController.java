package br.com.zup.propostas.cartao;

import br.com.zup.propostas.cartao.BloqueioCartao;
import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.cartao.CartaoRepository;
import br.com.zup.propostas.cartao.integracao.BloqueioCartaoRequest;
import br.com.zup.propostas.cartao.integracao.BloqueioCartaoResponse;
import br.com.zup.propostas.cartao.integracao.CartaoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/cartoes")
public class BloqueioCartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @PostMapping("/{id}/bloqueios")
    public ResponseEntity<?> bloquearCartao(@PathVariable("id") long id, @RequestHeader HttpHeaders headers, HttpServletRequest httpRequest){

        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        if(cartao.cartaoBloqueado()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já se encontra bloqueado");
        }

        String ipCliente = httpRequest.getRemoteAddr();
        String userAgent = headers.get(HttpHeaders.USER_AGENT).get(0);

        BloqueioCartaoResponse bloqueioResponse = cartaoClient.bloqueiaCartao(cartao.getNumeroCartao(), new BloqueioCartaoRequest("proposta-microservice"));

        if(bloqueioResponse.getResultado().equals("BLOQUEADO")){
            cartao.bloquearCartao(ipCliente, userAgent);
            cartaoRepository.save(cartao);
            return ResponseEntity.ok().build();
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Houve um erro ao solicitar o bloqueio no sistema bancário");
    }
}
