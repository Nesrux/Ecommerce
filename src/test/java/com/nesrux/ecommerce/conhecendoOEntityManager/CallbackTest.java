package com.nesrux.ecommerce.conhecendoOEntityManager;

import com.nesrux.ecommerce.model.Cliente;
import com.nesrux.ecommerce.model.Pedido;
import com.nesrux.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import java.math.BigDecimal;

public class CallbackTest extends EntityManagerTest {
    @Test
    public void testarCalbackAtualizacaoECriacao() {
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.PAGO);
        pedido.setCliente(entityManager.find(Cliente.class, 1));
        pedido.setTotal(BigDecimal.TEN);

        entityManager.getTransaction().begin();

        entityManager.persist(pedido);
        entityManager.flush();

        pedido.setStatus(StatusPedido.CANCELADO);
        entityManager.flush();

        entityManager.getTransaction().commit();
        entityManager.clear();

        System.out.println(pedido.getDataCriacao());
        System.out.println(pedido.getDataUltimaAtualizacao());

        Assert.assertNotEquals(pedido.getDataCriacao(),
                pedido.getDataUltimaAtualizacao());


    }

}
