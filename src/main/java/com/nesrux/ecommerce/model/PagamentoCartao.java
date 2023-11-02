package com.nesrux.ecommerce.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    private String numero;
}
