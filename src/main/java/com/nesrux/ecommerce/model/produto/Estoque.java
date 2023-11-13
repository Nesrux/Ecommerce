package com.nesrux.ecommerce.model.produto;

import com.nesrux.ecommerce.model.EntidadeBaseInteger;
import com.nesrux.ecommerce.model.produto.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "estoque")
public class Estoque extends EntidadeBaseInteger {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;
}
