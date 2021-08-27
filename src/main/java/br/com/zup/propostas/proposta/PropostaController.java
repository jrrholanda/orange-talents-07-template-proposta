package br.com.zup.propostas.proposta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class PropostaController {

    private PropostaRepository repository;

    public PropostaController(PropostaRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/api/propostas")
    public ResponseEntity<?> novaProposta(@Valid @RequestBody NovaPropostaRequest request, UriComponentsBuilder uriComponentsBuilder){

        Proposta proposta = request.toModel();
        repository.save(proposta);

        URI link = uriComponentsBuilder.path("/api/propostas/{id}").buildAndExpand(proposta.getId()).toUri();

        return  ResponseEntity.created(link).build();
    }
}
