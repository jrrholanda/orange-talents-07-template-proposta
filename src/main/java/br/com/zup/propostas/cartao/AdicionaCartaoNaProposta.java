package br.com.zup.propostas.cartao;

import br.com.zup.propostas.cartao.integracao.CartaoClient;
import br.com.zup.propostas.cartao.integracao.CartaoRequest;
import br.com.zup.propostas.cartao.integracao.CartaoResponse;
import br.com.zup.propostas.proposta.Proposta;
import br.com.zup.propostas.proposta.PropostaRepository;
import br.com.zup.propostas.proposta.StatusProposta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;


//@EnableScheduling
//@EnableAsync
@Component
public class AdicionaCartaoNaProposta {

    @Autowired
    private CartaoClient cartaoClient;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private TransactionTemplate transactionManager;

    private static final long ONE_MINUTE = 60 * 1000;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(AdicionaCartaoNaProposta.class);

    @Scheduled(initialDelay = ONE_MINUTE, fixedDelay = ONE_MINUTE)
    public void executa() {

        logger.info("verificando propostas elegíveis que não possuem cartões atrelados");

                List<Proposta> propostasElegiveis = propostaRepository.findByStatus(StatusProposta.ELEGIVEL);

                propostasElegiveis.forEach(proposta -> {
                    CartaoRequest cartaoRequest = new CartaoRequest(proposta);
                    CartaoResponse cartaoResponse = cartaoClient.buscaCartao(cartaoRequest);

                    Cartao novoCartao = cartaoResponse.toModel(proposta);
                    cartaoRepository.save(novoCartao);

                    proposta.adicionarCartao(novoCartao);
                    propostaRepository.save(proposta);

                });
    }
}
