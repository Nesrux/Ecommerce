package com.nesrux.ecommerce.conhecendoOEntityManager;

import com.nesrux.ecommerce.model.Pedido;
import com.nesrux.ecommerce.model.StatusPedido;
import org.junit.Test;
import util.EntityManagerTest;

public class FlushTest extends EntityManagerTest {
    @Test(expected = Exception.class)
    public void chamarFlush() {
        try {
            entityManager.getTransaction().begin();
            Pedido pedido = entityManager.find(Pedido.class, 1);
            pedido.setStatus(StatusPedido.PAGO);

            if (pedido.getPagamento() == null) {
                throw new RuntimeException("Pedido ainda nao foi pago");
            }
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }


    }

}
