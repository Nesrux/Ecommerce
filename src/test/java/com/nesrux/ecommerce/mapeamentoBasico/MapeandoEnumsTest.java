package com.nesrux.ecommerce.mapeamentoBasico;

import com.nesrux.ecommerce.model.Cliente;
import com.nesrux.ecommerce.model.SexoCliente;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

public class MapeandoEnumsTest extends EntityManagerTest {

    @Test
    public void testarEnum(){
        Cliente cliente = new Cliente();
        cliente.setId(4);
        cliente.setNome("Maria");
        cliente.setSexo(SexoCliente.FEMININO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clienteSalvo = entityManager.find(Cliente.class, 4);
        Assert.assertNotNull(clienteSalvo);

    }
}
