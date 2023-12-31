package com.nesrux.ecommerce.model.Pedido;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("boleto")
//@Table(name = "pagamento_boleto")
public class PagamentoBoleto extends Pagamento {

    @Column(name = "codigo_barras", length = 100)
    private String codigoBarras;
    /*DescriminationValue, serve para definir oque o JPa vai deixar na coluna
     * dtype (padrão), por padrão o valor de desciminação é o nome da classe, mas
     * da para customizar isso,*/
}
