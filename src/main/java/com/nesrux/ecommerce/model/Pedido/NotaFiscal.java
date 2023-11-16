package com.nesrux.ecommerce.model.Pedido;

import com.nesrux.ecommerce.model.EntidadeBaseInteger;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal extends EntidadeBaseInteger {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "pedido_id")
    private Integer id;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_nota_fiscal_pedido"))
    private Pedido pedido;

    //Lob = large object
    @Lob
    @Column(nullable = false)
    private byte[] xml;

    @Column(name = "data_emissao", nullable = false)
    private Date dataEmissao;
}

