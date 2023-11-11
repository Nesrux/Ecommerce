package com.nesrux.ecommerce.mapeamentoBasico;

import com.nesrux.ecommerce.model.cliente.Cliente;
import com.nesrux.ecommerce.model.cliente.SexoCliente;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

public class MapeandoEnumsTest extends EntityManagerTest {

    @Test
    public void testarEnum(){
        Cliente cliente = new Cliente();
        cliente.setNome("Maria");
        cliente.setSexo(SexoCliente.FEMININO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clienteSalvo = entityManager.find(Cliente.class, 3);
        System.out.println(clienteSalvo.getNome());
        Assert.assertNotNull(clienteSalvo);

    }
}
