package br.com.zup.propostas.notificacaoviagem;

import br.com.zup.propostas.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.HttpHeaders;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class NotificacaoViagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String destino;

    @NotNull
    private LocalDateTime dataRetorno;

    @ManyToOne
    private Cartao cartao;

    @NotBlank
    private String ipCliente;

    @NotBlank
    private String userAgent;

    @CreationTimestamp
    private LocalDateTime instanteNotificacao;

    @Deprecated
    public NotificacaoViagem() {
    }

    public NotificacaoViagem(String destino, LocalDateTime dataRetorno, Cartao cartao, String ipCliente, String userAgent) {
        this.destino = destino;
        this.dataRetorno = dataRetorno;
        this.cartao = cartao;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
    }
}
