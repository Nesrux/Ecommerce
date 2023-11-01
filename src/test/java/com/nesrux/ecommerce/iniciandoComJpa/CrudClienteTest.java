package com.nesrux.ecommerce.iniciandoComJpa;

import com.nesrux.ecommerce.model.Cliente;
import org.junit.Test;
import util.EntityManagerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CrudClienteTest extends EntityManagerTest {

    @Test
    public void CriarUsuario() {
        Cliente cliente = new Cliente();
        cliente.setNome("Marcelo amorin");
        cliente.setId(3);
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clientePersistido = entityManager.find(Cliente.class, 3);
        assertNotNull(cliente);

        assertEquals(cliente, clientePersistido);
    }


}
