package br.com.zup.propostas.carteira;

import br.com.zup.propostas.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class CarteiraDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CarteiraEnum carteira;

    @NotBlank @Email
    private String email;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public CarteiraDigital() {
    }

    public CarteiraDigital(CarteiraEnum carteira, String email, Cartao cartao) {
        this.carteira = carteira;
        this.email = email;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public CarteiraEnum getCarteira() {
        return carteira;
    }
}
