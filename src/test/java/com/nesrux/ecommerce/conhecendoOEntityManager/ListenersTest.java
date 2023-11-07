package com.nesrux.ecommerce.conhecendoOEntityManager;

import com.nesrux.ecommerce.model.Cliente;
import com.nesrux.ecommerce.model.Pedido;
import com.nesrux.ecommerce.model.StatusPedido;
import org.junit.Test;
import util.EntityManagerTest;

import java.math.BigDecimal;

public class ListenersTest extends EntityManagerTest {
    @Test
    public void testaarListenersDoJpa() {
        Pedido pedido = new Pedido();
        pedido.setCliente(entityManager.find(Cliente.class, 1));
        pedido.setTotal(BigDecimal.TEN);

        entityManager.getTransaction().begin();

        entityManager.persist(pedido);
        entityManager.flush();

        pedido.setStatus(StatusPedido.PAGO);
        entityManager.flush();

        entityManager.getTransaction().commit();
        entityManager.clear();

        /*Esse Teste verifica os listeners de notaFiscalService
         * e Nota fiscal Listener*/

    }

}
