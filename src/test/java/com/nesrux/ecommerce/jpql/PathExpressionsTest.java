package com.nesrux.ecommerce.jpql;

import com.nesrux.ecommerce.model.Pedido.Pedido;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.util.List;

public class PathExpressionsTest extends EntityManagerTest {
    @Test
    public void usarPathExpression() {
        String jpql = "select p from Pedido p where p.cliente.nome = 'NOME QUALQUER'";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> pedidos = typedQuery.getResultList();

        Assert.assertTrue(pedidos.isEmpty());

        /* path expressions é o caminho das classes até onde desejado por exemolo
         * eu quero um pedido que tenha um cliente e esse cliente tem o nome 'NOME qualquer'
         * entao pedido.cliente.nome */
    }

    @Test
    public void buscarPedidosComProdutosEspecificos() {
        String jpql = "select p from Pedido p join p.itens i where i.pedido.id = 1";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> pedidos = typedQuery.getResultList();

        Assert.assertFalse(pedidos.isEmpty());
    }
}
