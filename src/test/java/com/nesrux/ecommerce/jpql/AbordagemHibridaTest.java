package com.nesrux.ecommerce.jpql;

import com.nesrux.ecommerce.model.produto.Categoria;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class AbordagemHibridaTest extends EntityManagerTest {

    @BeforeClass
    public static void setUpBeforeClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");



        EntityManager em = entityManagerFactory.createEntityManager();
        String jpql = "select c from Categoria c";
        TypedQuery<Categoria> typedQuery = em.createQuery(jpql, Categoria.class);

        entityManagerFactory.addNamedQuery("Categoria.listar", typedQuery);
    }

    @Test
    public void usarAbordagemHibrida() {
        TypedQuery<Categoria> typedQuery = entityManager.createNamedQuery("Categoria.listar",
                Categoria.class);

        List<Categoria> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());
    }
}
