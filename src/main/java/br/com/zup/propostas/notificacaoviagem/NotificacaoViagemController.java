package br.com.zup.propostas.notificacaoviagem;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.cartao.CartaoRepository;
import br.com.zup.propostas.cartao.integracao.CartaoClient;
import br.com.zup.propostas.notificacaoviagem.integracao.AvisoViagemRequest;
import br.com.zup.propostas.notificacaoviagem.integracao.AvisoViagemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/cartoes")
public class NotificacaoViagemController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private NotificacaoViagemRepository notificacaoViagemRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @PostMapping("/{id}/avisos")
    public ResponseEntity<?> noficaViagem(@PathVariable("id") long id, @RequestBody @Valid NotificacaoViagemRequest notificaRequest,
                                          @RequestHeader HttpHeaders headers, HttpServletRequest httpRequest){

        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        String ipCliente = httpRequest.getRemoteAddr();
        String userAgent = headers.get(HttpHeaders.USER_AGENT).get(0);

        AvisoViagemRequest avisoViagemRequest = new AvisoViagemRequest(notificaRequest.getDestino(), notificaRequest.getDataRetorno());
        AvisoViagemResponse avisoViagemResponse = cartaoClient.notificacaoViagem(cartao.getNumeroCartao(), avisoViagemRequest);

        if(avisoViagemResponse.getResultado().equals("CRIADO")){
            NotificacaoViagem notificacaoViagem = notificaRequest.toModel(ipCliente, userAgent, cartao);
            cartao.adcionaNotificacoes(notificacaoViagem);
            notificacaoViagemRepository.save(notificacaoViagem);
            return ResponseEntity.ok().build();
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Houve um erro ao notificar o sistema bancário");
    }
}
