package com.nesrux.ecommerce.jpql;

import com.nesrux.ecommerce.model.Pedido.Pedido;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.util.List;

public class JoinTest extends EntityManagerTest {
    @Test
    public void fazerInnerJoinTest() {
    //    String jpql = "select p from Pedido p join p.pagamento pag";
           String jpql = "select p from Pedido p join p.itens i";

        TypedQuery<Object[]> pedidoQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> pedidos = pedidoQuery.getResultList();
        Assert.assertTrue(pedidos.size() == 2);
    }
}
