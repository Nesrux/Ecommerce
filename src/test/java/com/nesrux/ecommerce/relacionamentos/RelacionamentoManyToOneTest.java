package com.nesrux.ecommerce.relacionamentos;

import com.nesrux.ecommerce.model.Pedido.ItemPedido;
import com.nesrux.ecommerce.model.Pedido.ItemPedidoId;
import com.nesrux.ecommerce.model.Pedido.Pedido;
import com.nesrux.ecommerce.model.Pedido.StatusPedido;
import com.nesrux.ecommerce.model.cliente.Cliente;
import com.nesrux.ecommerce.model.produto.Produto;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelacionamentoManyToOneTest extends EntityManagerTest {
    @Test
    public void verificarRelacionamentoClientePedido() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setCliente(cliente);
        pedido.setTotal(new BigDecimal("152.50"));

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoSalvo = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoSalvo);
        Assert.assertNotNull(pedidoSalvo.getCliente());
        System.out.println(pedido.getId());
    }

    @Test
    public void verificarRelacionamentoItemPedido_com_produto_e_pedido() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(BigDecimal.TEN);


        entityManager.getTransaction().begin();

        entityManager.persist(pedido);
        entityManager.flush();

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId(pedido.getId(), produto.getId()));
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setPrecoProduto(produto.getPreco());
        itemPedido.setQuantidade(1);

        entityManager.persist(itemPedido);

        entityManager.getTransaction().commit();
        entityManager.clear();

        ItemPedido itemPedidoPersistido = entityManager.find(ItemPedido.class,
                new ItemPedidoId(pedido.getId(), produto.getId()));

        Assert.assertNotNull(itemPedidoPersistido);
        Assert.assertNotNull(itemPedidoPersistido.getProduto());
    }
}
