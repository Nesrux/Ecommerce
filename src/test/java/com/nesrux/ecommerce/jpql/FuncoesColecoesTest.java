package com.nesrux.ecommerce.jpql;

import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.util.List;

public class FuncoesColecoesTest extends EntityManagerTest {
    @Test
    public void aplicarfuncoescolecoes() {
        String jpql = "select size(p.itens) from Pedido p where size(p.itens) > 1";

        TypedQuery<Integer> typedQuery = entityManager.createQuery(jpql, Integer.class);

        List<Integer> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
        lista.forEach(size -> System.out.println("size: " + size));
    }
}
