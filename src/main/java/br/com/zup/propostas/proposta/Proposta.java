package br.com.zup.propostas.proposta;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.validacao.CPFOrCNPJ;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank @Email
    private String email;
    @NotBlank @CPFOrCNPJ
    private String documento;
    @NotNull
    @Positive
    private BigDecimal salario;
    @NotBlank
    private String endereco;
    @Enumerated(EnumType.STRING)
    private StatusProposta status = StatusProposta.NAO_ELEGIVEL;

    @OneToOne(mappedBy = "proposta", cascade = CascadeType.MERGE)
    private Cartao cartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String nome, String email, String documento, BigDecimal salario, String endereco) {
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.salario = salario;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getEndereco() {
        return endereco;
    }

    public StatusProposta getStatus() {
        return status;
    }

    public void atualizaStatus(StatusProposta status) {
        if (status == null)
            throw new IllegalArgumentException("Status não pode ser nulo");
        this.status = status;
    }

    public void adicionarCartao(Cartao novoCartao){
        if(novoCartao == null){
            throw new IllegalArgumentException("Não é possível adicionar um cartão nulo a proposta");
        }
        this.cartao = novoCartao;
        this.atualizaStatus(StatusProposta.ELEGIVEL_COM_CARTAO);
    }
}
