package br.com.zup.propostas.carteira;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.cartao.CartaoRepository;
import br.com.zup.propostas.cartao.integracao.CartaoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/cartoes")
public class CarteiraController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @PostMapping("/{id}/carteiras")
    public ResponseEntity<?> associaCartao(@PathVariable("id") long id, @RequestBody @Valid InclusaoCarteiraRequest carteiraRequest, UriComponentsBuilder uriComponentsBuilder) {

        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        InclusaoCarteiraResponse carteiraResponse = cartaoClient.associaCarteira(cartao.getNumeroCartao(), carteiraRequest);

        CarteiraDigital carteira = carteiraRequest.toModel(cartao);

        if (cartao.verificaCarteira(carteira)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Carteira já cadastrada no cartão");
        }

        if (carteiraResponse.getResultado().equals("ASSOCIADA")) {
            carteiraRepository.save(carteira);
            cartao.adcionaCarteira(carteira);
            cartaoRepository.save(cartao);
            URI link = uriComponentsBuilder.path("/api/propostas/{id}").buildAndExpand(carteira.getId()).toUri();
            return ResponseEntity.created(link).build();
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Houve um erro ao notificar o sistema bancário");
    }
}
