package com.nesrux.ecommerce.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PagamentoBoleto {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;

    private Integer pedidoId;

    private StatusPagamento status;
    
    private String codigoBarras;
}
