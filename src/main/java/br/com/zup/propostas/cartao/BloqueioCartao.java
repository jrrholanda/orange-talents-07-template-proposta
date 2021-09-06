package br.com.zup.propostas.cartao;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class BloqueioCartao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private @Valid Cartao cartao;

    @NotBlank
    private String userAgent;

    @NotBlank
    private String ipCliente;

    @CreationTimestamp
    private LocalDateTime dataBloqueio;

    @Deprecated
    public BloqueioCartao() {
    }

    public BloqueioCartao(Cartao cartao, String userAgent, String ipCliente) {
        this.cartao = cartao;
        this.userAgent = userAgent;
        this.ipCliente = ipCliente;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public Long getId() {
        return id;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public LocalDateTime getDataBloqueio() {
        return dataBloqueio;
    }
}
