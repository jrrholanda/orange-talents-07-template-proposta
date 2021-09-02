package br.com.zup.propostas.cartao;

import br.com.zup.propostas.proposta.Proposta;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Cartao {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Proposta proposta;

    @NotBlank
    private String numeroCartao;

    private String titular;

    @Deprecated
    public Cartao() {
    }

    public Cartao(Proposta proposta, String numeroCartao, String titular) {
        this.proposta = proposta;
        this.numeroCartao = numeroCartao;
        this.titular = titular;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public Long getId() {
        return id;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public String getTitular() {
        return titular;
    }
}
