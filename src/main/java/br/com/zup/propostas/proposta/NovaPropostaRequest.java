package br.com.zup.propostas.proposta;

import br.com.zup.propostas.validacao.CPFOrCNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @NotBlank
    private String nome;
    @NotBlank @Email
    private String email;
    @NotBlank @CPFOrCNPJ
    private String documento;
    @NotNull @Positive
    private BigDecimal salario;
    @NotBlank
    private String endereco;

    public NovaPropostaRequest(String nome, String email, String documento, BigDecimal salario, String endereco) {
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.salario = salario;
        this.endereco = endereco;
    }

    public String getDocumento() {
        return documento;
    }

    public Proposta toModel(){
        return new Proposta(nome, email, documento, salario, endereco);
    }
}
