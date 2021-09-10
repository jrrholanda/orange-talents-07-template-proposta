package br.com.zup.propostas.carteira;

import br.com.zup.propostas.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class InclusaoCarteiraRequest {

    @Email @NotBlank
    private String email;

    @NotBlank
    private String carteira;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public InclusaoCarteiraRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    private CarteiraEnum toCarteira(){
        if(carteira.toUpperCase().equals("PAYPAL"))
            return CarteiraEnum.PAYPAL;
        return CarteiraEnum.SAMSUNG_PAY;
    }

    public CarteiraDigital toModel(Cartao cartao){
        return new CarteiraDigital(toCarteira(), this.email, cartao);
    }
}
