package com.nesrux.ecommerce.mapeamentoAvancado;

import com.nesrux.ecommerce.model.cliente.Cliente;
import com.nesrux.ecommerce.model.cliente.SexoCliente;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import java.time.LocalDate;

public class SecundaryTableTest extends EntityManagerTest {
    @Test
    public void salvarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setSexo(SexoCliente.MASCULINO);
        cliente.setCpf("1457895314");
        cliente.setDataNascimento(LocalDate.of(2001, 3, 5));

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clienteTeste = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteTeste);

        Assert.assertEquals(cliente.getDataNascimento(),
                LocalDate.of(2001, 3, 5));

        Assert.assertNotNull(cliente.getSexo());
    }
}
