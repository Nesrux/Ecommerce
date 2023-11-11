package com.nesrux.ecommerce.iniciandoComJpa;

import com.nesrux.ecommerce.model.cliente.Cliente;
import org.junit.Test;
import util.EntityManagerTest;

import static org.junit.Assert.*;

public class CrudClienteTest extends EntityManagerTest {

    @Test
    public void CriarUsuario() {
        Cliente cliente = new Cliente();
        cliente.setNome("Marcelo amorin");
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clientePersistido = entityManager.find(Cliente.class, 3);
        assertNotNull(cliente);

        assertEquals(cliente, clientePersistido);
    }

    @Test
    public void lerCliente() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        assertNotNull(cliente);
    }

    @Test
    public void atualizarCliente() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        entityManager.getTransaction().begin();
        cliente.setNome("Moacir");
        entityManager.merge(cliente);

        entityManager.getTransaction().commit();
        entityManager.clear();

        assertEquals("Moacir", cliente.getNome());
    }

    @Test
    public void deletarCliente() {
        Cliente cliente = entityManager.find(Cliente.class, 2);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clienteNulo = entityManager.find(Cliente.class, 2);
        System.out.println(cliente.getNome());
        assertNull(clienteNulo);
    }


}
