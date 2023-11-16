package com.nesrux.ecommerce.model.Pedido;

import com.nesrux.ecommerce.model.produto.Produto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "item_pedido")
public class ItemPedido {

    @EmbeddedId
    private ItemPedidoId id;

    //TODO ler sobre a anotação MapsId e como fazer chave composta no hibernate
    @MapsId("pedidoId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_item_pedido_pedido"))
    private Pedido pedido;

    @MapsId("produtoId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_item_pedido_produto"))
    private Produto produto;

    @Column(name = "preco_produto", nullable = false)
    private BigDecimal precoProduto;

    @Column(nullable = false)
    private Integer quantidade;
}
