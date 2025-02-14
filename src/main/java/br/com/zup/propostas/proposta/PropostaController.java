package br.com.zup.propostas.proposta;

import br.com.zup.propostas.proposta.integracao.PropostaClient;
import br.com.zup.propostas.proposta.integracao.ResultadoAnaliseResponse;
import br.com.zup.propostas.proposta.integracao.SolicitacaoAnaliseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class PropostaController {

    @Autowired
    private PropostaRepository repository;

    @Autowired
    private PropostaClient solicitacao;

    @Transactional
    @PostMapping("/api/propostas")
    public ResponseEntity<?> novaProposta(@Valid @RequestBody NovaPropostaRequest request, UriComponentsBuilder uriComponentsBuilder) {

        if (repository.existsByDocumento(request.getDocumento())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Proposta já existente");
        }

        Proposta proposta = request.toModel();
        repository.save(proposta);

        SolicitacaoAnaliseRequest solitacaoRequest = new SolicitacaoAnaliseRequest(proposta);
        ResultadoAnaliseResponse response = solicitacao.enviaParaAnalise(solitacaoRequest);

        proposta.atualizaStatus(response.getStatus());

        URI link = uriComponentsBuilder.path("/api/propostas/{id}").buildAndExpand(proposta.getId()).toUri();

        return ResponseEntity.created(link).build();
    }
}
