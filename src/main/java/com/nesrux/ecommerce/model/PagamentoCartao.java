package com.nesrux.ecommerce.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PagamentoCartao {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;

    private Integer pedidoId;

    private StatusPagamento status;

    private String numero;
}
