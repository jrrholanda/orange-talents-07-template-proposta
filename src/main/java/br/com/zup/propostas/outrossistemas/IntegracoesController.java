//package br.com.zup.propostas.outrossistemas;
//
//import br.com.zup.propostas.cartao.integracao.CartaoResponse;
//import br.com.zup.propostas.proposta.Proposta;
//import br.com.zup.propostas.proposta.integracao.SolicitacaoAnaliseRequest;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Random;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@RestController
//public class IntegracoesController {
//
//    private AtomicInteger propostaRandom = new AtomicInteger();
//
//    @PostMapping(value = "/solicitacoes")
//    public String avaliaDocumento(@RequestBody SolicitacaoAnaliseRequest request) {
//        int contAtual = propostaRandom.getAndIncrement();
//        if (contAtual % 2 != 0) {
//            return "COM_RESTRICAO";
//        }
//        return "SEM_RESTRICAO";
//    }
//
//    @PostMapping(value = "/busca-cartoes")
//    public CartaoResponse buscaNumeroCartao(@RequestBody Proposta proposta){
//        String[] numeroCartao = { "0000", "0000", "0000", "0000" };
//        int i = new Random().nextInt(4);
//
//        return new CartaoResponse(numeroCartao[i], proposta);
//    }
//}
