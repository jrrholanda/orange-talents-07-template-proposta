package br.com.zup.propostas.cartao;

import br.com.zup.propostas.cartao.CartaoRepository;
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
public class BiometriaController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BiometriaRepository biometriaRepository;

    @PostMapping("/{idCartao}/biometria")
    public ResponseEntity<?> postBiometria(@PathVariable long idCartao, @Valid @RequestBody BiometriaRequest request, UriComponentsBuilder uriComponentsBuilder) {

        Cartao cartao = cartaoRepository.findById(idCartao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Biometria biometria = request.toModel(cartao);
        cartao.adicionarBiometria(biometria);
        biometriaRepository.save(biometria);
        cartaoRepository.save(cartao);

        URI link = uriComponentsBuilder.path("/api/cartoes/biometria/{id}").buildAndExpand(biometria.getId()).toUri();

        return ResponseEntity.created(link).build();
    }
}
