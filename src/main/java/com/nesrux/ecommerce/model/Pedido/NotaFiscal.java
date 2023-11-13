package com.nesrux.ecommerce.model.Pedido;

import com.nesrux.ecommerce.model.EntidadeBaseInteger;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal extends EntidadeBaseInteger {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "pedido_id")
    private Integer id;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    //Lob = large object
    @Lob
    private byte[] xml;

    @Column(name = "data_emissao")
    private Date dataEmissao;
}

