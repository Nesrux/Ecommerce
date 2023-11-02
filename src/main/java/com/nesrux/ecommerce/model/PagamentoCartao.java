package com.nesrux.ecommerce.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "pagamento_cartao")
public class PagamentoCartao {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name="pedido_id")
    private Integer pedidoId;

    private StatusPagamento status;

    private String numero;
}
