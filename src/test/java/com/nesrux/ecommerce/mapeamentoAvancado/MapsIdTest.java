package com.nesrux.ecommerce.mapeamentoAvancado;

import com.nesrux.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class MapsIdTest extends EntityManagerTest {
    @Test
    public void inserirPagamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);
        notaFiscal.setDataEmissao(new Date());
        notaFiscal.setXml("<xml></xml>");

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal); //nota fiscal é o owner da relação
        entityManager.getTransaction().commit();
        entityManager.clear();

        NotaFiscal notaFiscalPersistida = entityManager.find(NotaFiscal.class,
                notaFiscal.getId());
        Assert.assertNotNull(notaFiscalPersistida);
        Assert.assertEquals(pedido.getId(), notaFiscalPersistida.getId());
    }

    @Test
    public void inserirPedido() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(produto.getPreco());

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setPrecoProduto(produto.getPreco());
        itemPedido.setQuantidade(1);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();
        ItemPedido ItemPedidoVerificacao = entityManager
                .find(ItemPedido.class,
                        new ItemPedidoId(pedido.getId(), produto.getId()));
        Assert.assertNotNull(ItemPedidoVerificacao);

    }

    @Test
    public void verificarPagamentoCartao() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(BigDecimal.TEN);

        PagamentoCartao pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
        pagamentoCartao.setNumero("123456789");

        pagamentoCartao.setPedido(pedido);
        pedido.setPagamento(pagamentoCartao);

        entityManager.getTransaction().begin();
        entityManager.persist(pagamentoCartao);
        entityManager.getTransaction().commit();
    }
}
