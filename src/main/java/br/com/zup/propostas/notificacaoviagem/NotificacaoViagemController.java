package br.com.zup.propostas.notificacaoviagem;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.cartao.CartaoRepository;
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

    @PostMapping("/{id}/notifica")
    public ResponseEntity<?> noficaViagem(@PathVariable("id") long id, @RequestBody @Valid NotificacaoViagemRequest notificaRequest, @RequestHeader HttpHeaders headers, HttpServletRequest httpRequest){

        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        String ipCliente = httpRequest.getRemoteAddr();
        String userAgent = headers.get(HttpHeaders.USER_AGENT).get(0);

        NotificacaoViagem notificacaoViagem = notificaRequest.toModel(ipCliente, userAgent, cartao);
        notificacaoViagemRepository.save(notificacaoViagem);

        return ResponseEntity.ok().build();
    }
}
