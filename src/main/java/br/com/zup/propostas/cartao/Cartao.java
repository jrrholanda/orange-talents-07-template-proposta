package br.com.zup.propostas.cartao;

import br.com.zup.propostas.carteira.CarteiraDigital;
import br.com.zup.propostas.notificacaoviagem.NotificacaoViagem;
import br.com.zup.propostas.proposta.Proposta;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private Set<Biometria> biometrias = new HashSet<>();

    @Enumerated//(EnumType.STRING)
    private StatusCartao status = StatusCartao.ATIVO;

    @OneToMany(mappedBy = "cartao")
    private Set<BloqueioCartao> listaBloqueios = new HashSet<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private Set<NotificacaoViagem> notificacoes = new HashSet<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private Set<CarteiraDigital> carteiras = new HashSet<>();

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

    public Set<Biometria> getBiometrias() {
        return biometrias;
    }

    public StatusCartao getStatus() {
        return status;
    }

    public Set<BloqueioCartao> getListaBloqueios() {
        return listaBloqueios;
    }

    public void adicionarBiometria(Biometria biometria){
        this.biometrias.add(biometria);
    }

    public void bloquearCartao(String ipCliente, String userAgent){
        this.listaBloqueios.add(new BloqueioCartao(this, userAgent, ipCliente));
        this.status = StatusCartao.BLOQUEADO;
    }

    public boolean cartaoBloqueado(){
        return this.status.equals(StatusCartao.BLOQUEADO);
    }

    public void adcionaNotificacoes(NotificacaoViagem notificacaoViagem) {
        this.notificacoes.add(notificacaoViagem);
    }

    public boolean verificaCarteira(CarteiraDigital carteiraDigital){
        return carteiras.stream().map(CarteiraDigital::getCarteira).equals(carteiraDigital.getCarteira());
    }

    public void adcionaCarteira(CarteiraDigital carteiraDigital){
        carteiras.add(carteiraDigital);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cartao)) return false;
        Cartao cartao = (Cartao) o;
        return Objects.equals(carteiras, cartao.carteiras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carteiras);
    }
}
