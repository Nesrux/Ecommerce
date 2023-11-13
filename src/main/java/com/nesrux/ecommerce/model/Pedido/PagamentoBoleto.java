package com.nesrux.ecommerce.model.Pedido;

import com.nesrux.ecommerce.model.EntidadeBaseInteger;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "pagamento_boleto")
public class PagamentoBoleto extends EntidadeBaseInteger {


    @Column(name = "pedido_id")
    private Integer pedidoId;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @Column(name = "codigo_barras")
    private String codigoBarras;
}
