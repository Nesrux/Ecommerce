package com.nesrux.ecommerce.jpql;

import com.nesrux.ecommerce.model.Pedido.Pedido;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.util.List;

public class JoinTest extends EntityManagerTest {

    @Test
    public void usarfatch() {
        /*Fetch faz ele obrigatóriamente carregar as propriedades
         * mesmo elas sendo leazy*/

        String jpql = "select p from Pedido p " +
                " left join fetch p.pagamento " +
                " join fetch p.cliente " +
                " left join fetch p.notaFiscal";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> pedidos = typedQuery.getResultList();
        Assert.assertFalse(pedidos.isEmpty());

    }

    @Test
    public void fazerInnerJoinTest() {
        String jpql = "select p from Pedido p join p.pagamento pag";
        //String jpql = "select p from Pedido p join p.itens i";
        TypedQuery<Object[]> pedidoQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> pedidos = pedidoQuery.getResultList();

        Assert.assertFalse(pedidos.isEmpty());
    }

    @Test
    public void fazerleftJoinTest() {
        String jpql = "select p from Pedido p left join p.pagamento pag";

        TypedQuery<Object[]> pedidoQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> pedidos = pedidoQuery.getResultList();

        Assert.assertFalse(pedidos.isEmpty());
    }


}
