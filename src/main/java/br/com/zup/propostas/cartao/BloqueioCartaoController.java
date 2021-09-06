package br.com.zup.propostas.cartao;

import br.com.zup.propostas.cartao.BloqueioCartao;
import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.cartao.CartaoRepository;
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

    @GetMapping("/bloqueio")
    public ResponseEntity<?>notFound(){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "É necessário informar o id do cartão na url");
    }

    @PostMapping("/{id}/bloqueio")
    public ResponseEntity<?> bloquearCartao(@PathVariable("id") long id, @RequestHeader HttpHeaders headers, HttpServletRequest httpRequest){

        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        if(cartao.cartaoBloqueado()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já se encontra bloqueado");
        }

        String ipCliente = httpRequest.getRemoteAddr();
        String userAgent = headers.get(HttpHeaders.USER_AGENT).get(0);

        cartao.bloquearCartao(ipCliente, userAgent);
        cartaoRepository.save(cartao);

        return ResponseEntity.ok().build();
    }
}
