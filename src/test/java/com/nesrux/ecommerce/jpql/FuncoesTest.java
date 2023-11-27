package com.nesrux.ecommerce.jpql;

import com.nesrux.ecommerce.model.Pedido.Pedido;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.TimeZone;

public class FuncoesTest extends EntityManagerTest {
    @Test
    public void aplicarfuncoescolecoes() {
        String jpql = "select size(p.itens) from Pedido p where size(p.itens) > 1";

        TypedQuery<Integer> typedQuery = entityManager.createQuery(jpql, Integer.class);

        List<Integer> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
        lista.forEach(size -> System.out.println("size: " + size));
    }
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

    @Test
    public void aplicarfuncoesNativas() {
        String jpql = "select p from Pedido p where function('acima_media_faturamento', p.total) = 1";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }
    @Test
    public void aplicarFuncaoColecao() {
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
    @Test
    public void aplicarFuncaoComNumeros() {
        String jpql = "select abs(-10), mod(3,2), sqrt(9) from Pedido";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
        lista.forEach(arr -> System.out.println(arr[0] + " - " + arr[1] + " - " + arr[2]));
    }

}
