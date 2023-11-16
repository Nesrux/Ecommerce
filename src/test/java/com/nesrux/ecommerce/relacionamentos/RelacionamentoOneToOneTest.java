package com.nesrux.ecommerce.relacionamentos;

import com.nesrux.ecommerce.model.Pedido.*;
import com.nesrux.ecommerce.model.cliente.Cliente;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class RelacionamentoOneToOneTest extends EntityManagerTest {
    @Test
    public void verificarRelacionamentoPedido() {
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.PAGO);
        pedido.setCliente(entityManager.find(Cliente.class, 1));
        pedido.setTotal(BigDecimal.TEN);

        PagamentoCartao pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.setPedido(pedido);
        pagamentoCartao.setNumeroCartao("123456");
        pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);

        pedido.setPagamento(pagamentoCartao);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.persist(pagamentoCartao);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoPersistido = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoPersistido.getPagamento());
        System.out.println(pedidoPersistido.getPagamento().getStatus());
    }

    @Test
    public void verificarRelacionamentoPedidoComNotaFiscal() {
        Pedido pedido = new Pedido();
        pedido.setCliente(entityManager.find(Cliente.class, 1));
        pedido.setStatus(StatusPedido.PAGO);
        pedido.setTotal(BigDecimal.TEN);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);
        notaFiscal.setXml("Esse é p famoso 16 toneladas turu turu turu".getBytes());
        notaFiscal.setDataEmissao(new Date(2022, 5, 7));

        pedido.setNotaFiscal(notaFiscal);

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoPersistido = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoPersistido.getNotaFiscal());
    }
}
