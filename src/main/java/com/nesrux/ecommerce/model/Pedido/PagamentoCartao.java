package com.nesrux.ecommerce.model.Pedido;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@DiscriminatorValue("PagamentoCartao")
public class PagamentoCartao extends Pagamento {

    @Column(name = "numero_cartao")
    private String numeroCartao;

    /*DescriminationValue, serve para definir oque o JPa vai deixar na coluna
     * dtype (padrão), por padrão o valor de desciminação é o nome da classe, mas
     * da para customizar isso,*/
}
