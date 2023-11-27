package com.nesrux.ecommerce.jpql;

import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.util.List;

public class FuncoesComNumerosTest extends EntityManagerTest {
    @Test
    public void aplicarFuncaoComNumeros() {
      String jpql = "select abs(-10), mod(3,2), sqrt(9) from Pedido";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
        lista.forEach(arr -> System.out.println(arr[0] + " - " + arr[1] + " - " + arr[2]));
    }
}
