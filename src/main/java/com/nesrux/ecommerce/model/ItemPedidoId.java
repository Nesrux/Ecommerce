package com.nesrux.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoId implements Serializable {
    /*Essa classe é uma classe de ChaveComposta no banco de dados,
     * posteriormente estudar sobre isso*/

    @EqualsAndHashCode.Include
    private Integer pedidoId;

    @EqualsAndHashCode.Include
    private Integer produtoId;

}
