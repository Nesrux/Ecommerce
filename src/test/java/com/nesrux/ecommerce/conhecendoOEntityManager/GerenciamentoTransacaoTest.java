package com.nesrux.ecommerce.conhecendoOEntityManager;

import com.nesrux.ecommerce.model.Pedido;
import com.nesrux.ecommerce.model.StatusPedido;
import org.junit.Test;
import util.EntityManagerTest;

public class GerenciamentoTransacaoTest extends EntityManagerTest {

    @Test
    public void abrirEFecharTransacao() {
        Pedido pedido = entityManager.find(Pedido.class, 1);
        entityManager.getTransaction().begin();

        pedido.setStatus(StatusPedido.PAGO);

        if (pedido.getPagamento() != null) {
            entityManager.getTransaction().commit();
        } else
            entityManager.getTransaction().rollback();
    }
}

