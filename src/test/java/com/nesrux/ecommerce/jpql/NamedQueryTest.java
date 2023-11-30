package com.nesrux.ecommerce.jpql;

import com.nesrux.ecommerce.model.produto.Produto;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.util.List;

public class NamedQueryTest extends EntityManagerTest {

    @Test
    public void excutarConsulta() {
        TypedQuery<Produto> typedQuery = entityManager.createNamedQuery("produtoListar",
                Produto.class);
        List<Produto> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void excutarConsultaDe1Entidade() {
        TypedQuery<Produto> typedQuery = entityManager.createNamedQuery("produtoBuscar",
                Produto.class);
        typedQuery.setParameter("produtoId", 1);

        Produto produto = typedQuery.getSingleResult();

        Assert.assertNotNull(produto);
    }

    @Test
    public void excutarConsultaPorCategoria() {
        TypedQuery<Produto> typedQuery = entityManager.createNamedQuery("produtoPorCategoria",
                Produto.class);
        typedQuery.setParameter("categoria", 2);

        List<Produto> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

}
