package com.nesrux.ecommerce.relacionamentos;

import com.nesrux.ecommerce.model.Pedido.Pedido;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

public class RemovendoEntidadeReferenciadaTest extends EntityManagerTest {
    @Test
    public void removerEntidadeRelacionada() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        entityManager.getTransaction().begin();
        /*Isso tem que ser feito dessa maneira, pois caso eu tente deletar a entidade Pedido, que possui
         * dados associados à ela como, os items do pedido, vai resultar em uma execption por causa de dados
         * inconsistentes dentro do banco de dados, onde o item de um pedido vai apontar para FK de um pedido
         * que nao existe*/
        //Também da para fazer com Streams
        pedido.getItens().forEach(item -> entityManager.remove(item));
        entityManager.remove(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoExcluido = entityManager.find(Pedido.class, 1);
        Assert.assertNull(pedidoExcluido);
    }

}
