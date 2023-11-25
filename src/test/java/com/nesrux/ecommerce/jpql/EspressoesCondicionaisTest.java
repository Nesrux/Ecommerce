package com.nesrux.ecommerce.jpql;

import com.nesrux.ecommerce.model.cliente.Cliente;
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
}
