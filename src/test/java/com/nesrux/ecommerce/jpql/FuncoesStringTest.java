package com.nesrux.ecommerce.jpql;

import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.util.List;

public class FuncoesStringTest extends EntityManagerTest {
    @Test
    public void aplicarFuncaoString() {
        //String jpql = "Select c.nome, concat('Categoria:', c.nome) from Categoria c";
        //String jpql = "Select c.nome, locate('a', c.nome) from Categoria c";
        //String jpql = "Select c.nome, substring(c.nome, 1, 2) from Categoria c";
        String jpql = "Select c.nome, upper(c.nome) from Categoria c";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
        lista.forEach(arr -> System.out.println(arr[0] + " - " + arr[1]));
    }
}
