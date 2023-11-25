package com.nesrux.ecommerce.jpql;

import com.nesrux.ecommerce.model.Pedido.Pedido;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.util.List;

public class OperadoresLogicosTest extends EntityManagerTest {
    @Test
    public void usarOperadorLogico() {
//        String jpql = "select p from Pedido p where" +
//                " p.total > 500 and p.status = 'AGUARDANDO' and p.cliente.id = 1";
//
        String jpql = "select p from Pedido p where p.status = 'AGUARDANDO' or p.status = 'PAGO'";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }
}
