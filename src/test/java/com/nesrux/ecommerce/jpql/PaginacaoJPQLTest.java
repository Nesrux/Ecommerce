package com.nesrux.ecommerce.jpql;

import com.nesrux.ecommerce.model.produto.Categoria;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.util.List;

public class PaginacaoJPQLTest extends EntityManagerTest {
    @Test
    public void paginarResultados() {
        String jpql = "Select c from Categoria c order by c.nome";

        TypedQuery<Categoria> typedQuery = entityManager.createQuery(jpql, Categoria.class);
        typedQuery.setFirstResult(0);
        typedQuery.setMaxResults(2);

        List<Categoria> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
        lista.forEach(c -> System.out.println(c.getId() + ", " + c.getNome()));
    }
}
