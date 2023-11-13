package com.nesrux.ecommerce.model.Pedido;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("PagamentoBoleto")
public class PagamentoBoleto extends Pagamento {

    @Column(name = "codigo_barras")
    private String codigoBarras;
    /*DescriminationValue, serve para definir oque o JPa vai deixar na coluna
     * dtype (padrão), por padrão o valor de desciminação é o nome da classe, mas
     * da para customizar isso,*/
}
