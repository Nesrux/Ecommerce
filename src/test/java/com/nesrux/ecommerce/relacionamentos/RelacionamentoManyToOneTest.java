package com.nesrux.ecommerce.relacionamentos;

import com.nesrux.ecommerce.model.Cliente;
import com.nesrux.ecommerce.model.Pedido;
import com.nesrux.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelacionamentoManyToOneTest extends EntityManagerTest {
    @Test
    public void verificarRelacionamento() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setCliente(cliente);
        pedido.setTotal(new BigDecimal("152.50"));

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoSalvo = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoSalvo);
        Assert.assertNotNull(pedidoSalvo.getCliente());
        System.out.println(pedido.getId());
    }
}
