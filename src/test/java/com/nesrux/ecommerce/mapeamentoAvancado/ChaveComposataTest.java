package com.nesrux.ecommerce.mapeamentoAvancado;

import com.nesrux.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import java.time.LocalDateTime;

public class ChaveComposataTest extends EntityManagerTest {
    @Test
    public void SalvarItem() {
        entityManager.getTransaction().begin();

        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(produto.getPreco());

        entityManager.persist(pedido);
        entityManager.flush();
        /*maps id*/

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId(pedido.getId(),
                produto.getId()));
//        itemPedido.setPedidoId(pedido.getId());
//        itemPedido.setProdutoId(produto.getId());
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setPrecoProduto(produto.getPreco());
        itemPedido.setQuantidade(1);


        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao);
        Assert.assertFalse(pedidoVerificacao.getItens().isEmpty());
    }

    @Test
    public void buscarItem() {
        ItemPedido itemPedido = entityManager.find(ItemPedido.class,
                new ItemPedidoId(1, 1));

        Assert.assertNotNull(itemPedido);
    }
}
