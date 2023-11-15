package com.nesrux.ecommerce.model.Pedido;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("cartao")
//@Table(name =  "pagamento_cartao")
public class PagamentoCartao extends Pagamento {

    @Column(name = "numero_cartao", nullable = false,  length = 50)
    private String numeroCartao;

    /*DescriminationValue, serve para definir oque o JPa vai deixar na coluna
     * dtype (padrão), por padrão o valor de desciminação é o nome da classe, mas
     * da para customizar isso,*/
}
