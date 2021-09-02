package br.com.zup.propostas.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/propostas")
public class StatusPropostaController {

    @Autowired
    private PropostaRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id){

        return repository.findById(id)
                .map(resp -> ResponseEntity.ok(new StatusPropostaResponse(resp)))
                .orElse(ResponseEntity.notFound().build());
    }
}
