package com.nesrux.ecommerce.conhecendoOEntityManager;

import com.nesrux.ecommerce.model.Pedido.Pedido;
import com.nesrux.ecommerce.model.Pedido.StatusPedido;
import org.junit.Test;
import util.EntityManagerTest;

public class GerenciamentoTransacaoTest extends EntityManagerTest {

    @Test(expected = Exception.class)
    public void abrirEFecharTransacao() {
        try {
            entityManager.getTransaction().begin();
            metodoDenegocio();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }


    }

    private void metodoDenegocio() {
        Pedido pedido = entityManager.find(Pedido.class, 1);
        pedido.setStatus(StatusPedido.PAGO);

        if (pedido.getPagamento() == null) {
            throw new RuntimeException("Pedido ainda nao foi pago");
        }
    }
}

