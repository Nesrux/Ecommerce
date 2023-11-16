package com.nesrux.ecommerce.conhecendoOEntityManager;

import com.nesrux.ecommerce.model.cliente.Cliente;
import com.nesrux.ecommerce.model.Pedido.Pedido;
import com.nesrux.ecommerce.model.Pedido.StatusPedido;
import org.junit.Test;
import util.EntityManagerTest;

import java.math.BigDecimal;

public class ListenersTest extends EntityManagerTest {
    @Test
    public void testaarListenersDoJpa() {
        Cliente cliente  = entityManager.find(Cliente.class, 1);
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setTotal(BigDecimal.TEN);
        pedido.setStatus(StatusPedido.AGUARDANDO);

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
