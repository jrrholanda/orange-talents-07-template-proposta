package br.com.zup.propostas.cartao;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    @Override
    <S extends Cartao> boolean exists(Example<S> example);
}
