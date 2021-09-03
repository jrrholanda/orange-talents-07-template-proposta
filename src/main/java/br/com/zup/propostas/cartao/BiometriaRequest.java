package br.com.zup.propostas.cartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import java.util.Base64;

public class BiometriaRequest {

    @NotBlank
    private String fingerprint ;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BiometriaRequest(String fingerprint ) {
        this.fingerprint  = Base64.getEncoder().encodeToString(fingerprint.getBytes());;
    }

    public Biometria toModel(Cartao cartao) {
        return new Biometria(this.fingerprint , cartao);
    }
}
