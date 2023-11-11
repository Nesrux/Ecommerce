package com.nesrux.ecommerce.mapeamentoAvancado;

import com.nesrux.ecommerce.model.cliente.Cliente;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

public class PropriedadeTreansisentTest extends EntityManagerTest {
    @Test
    public void validarPrimeiroNome() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Assert.assertNotNull(cliente.getPrimeiroNome());
    }
    /*Propriedades transientes são propriedades de classes do que o Jpa ignora*/
}
