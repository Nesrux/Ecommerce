package com.nesrux.ecommerce.relacionamentos;

import com.nesrux.ecommerce.model.PagamentoCartao;
import com.nesrux.ecommerce.model.Pedido;
import com.nesrux.ecommerce.model.StatusPagamento;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

public class RelacionamentoOneToOneTest extends EntityManagerTest {
    @Test
    public void verificarRelacionamentoPedido() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        PagamentoCartao pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.setPedido(pedido);
        pagamentoCartao.setNumero("123456");
        pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);

        entityManager.getTransaction().begin();
        entityManager.persist(pagamentoCartao);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoPersistido = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoPersistido.getPagamento());
        System.out.println(pedidoPersistido.getPagamento().getNumero());
    }
}
