package com.nesrux.ecommerce.jpql;

import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.TimeZone;

public class FuncoesDataTest extends EntityManagerTest {
    @Test
    public void aplicarFuncaoString() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

//        String jpql = "select current_date, current_time, current_timestamp from Pedido p " +
//                "where p.dataCriacao < current_date";
        //também existe o hour() minute() second() para  funções de data

        String jpql = "select year(p.dataCriacao), month(p.dataCriacao), day(p.dataCriacao) from Pedido p";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
        lista.forEach(arr -> System.out.println(arr[0] + " - " + arr[1] + " - " + arr[2]));
    }
}
