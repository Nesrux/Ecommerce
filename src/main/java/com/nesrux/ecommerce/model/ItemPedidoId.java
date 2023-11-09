package com.nesrux.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ItemPedidoId implements Serializable {
    /*Essa classe é uma classe de ChaveComposta no banco de dados,
     * posteriormente estudar sobre isso*/

    @EqualsAndHashCode.Include
    @Column(name = "pedido_id")
    private Integer pedidoId;
    /*Chave composta*/

    @EqualsAndHashCode.Include
    @Column(name = "produto_id")
    private Integer produtoId;

}
