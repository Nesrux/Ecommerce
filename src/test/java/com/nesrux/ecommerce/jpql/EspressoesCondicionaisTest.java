package com.nesrux.ecommerce.jpql;

import com.nesrux.ecommerce.model.cliente.Cliente;
import com.nesrux.ecommerce.model.produto.Produto;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.util.List;

public class EspressoesCondicionaisTest extends EntityManagerTest {
    @Test
    public void usarLike() {
        String jpql = "select c from Cliente c where c.nome like concat('%', :nome, '%')";
        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);
        typedQuery.setParameter("nome", "a");

        List<Cliente> clientes = typedQuery.getResultList();
        Assert.assertFalse(clientes.isEmpty());
    }

    @Test
    public void usarIsnull() {
        String jpql = "select p from Produto p where p.foto is null";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarIsEmpty() {
        String jpql = "select p from Produto p where p.categorias is empty";
        TypedQuery<?> typedQuery = entityManager.createQuery(jpql, Object.class);
        List<?> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

}
