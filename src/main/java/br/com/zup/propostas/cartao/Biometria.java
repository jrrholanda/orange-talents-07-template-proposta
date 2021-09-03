package br.com.zup.propostas.cartao;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String fingerprint;

    @ManyToOne
    private Cartao cartao;

    @CreationTimestamp
    private LocalDateTime criadaEm;

    @Deprecated
    public Biometria() {
    }

    public Biometria(String fingerprint , Cartao cartao) {
        this.fingerprint  = fingerprint ;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getFingerprint () {
        return fingerprint ;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
