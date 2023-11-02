package com.nesrux.ecommerce.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "pagamento_boleto")
public class PagamentoBoleto {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name = "pedido_id")
    private Integer pedidoId;

    private StatusPagamento status;

    @Column(name = "codigo_barras")
    private String codigoBarras;
}