package com.nesrux.ecommerce.jpql;

import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.util.List;

public class GroupByTest extends EntityManagerTest {


    @Test
    public void aplicarAgrupamentos() {
//        String jpql = "select c.nome, count(p.id) from Categoria c join c.produtos p " +
//                "group by c.id";
//        String jpql = "select concat(year(p.dataCriacao), function('monthname', 'p.dataCriacao')), " +
//                "sum(p.total) from Pedido p" +
//                " group by year(p.dataCriacao), month(p.dataCriacao)";
        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido" +
                " ip join ip.produto pro join pro.categorias c" +
                " group by c.id";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
        lista.forEach(arr -> System.out.println(arr[0] + "-" + arr[1]));
    }
}
