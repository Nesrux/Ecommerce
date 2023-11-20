package com.nesrux.ecommerce.jpql;

import com.nesrux.ecommerce.model.Pedido.Pedido;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;

public class BasicojpqlTest extends EntityManagerTest {

    /*JPQL é java persistence query languague*/

    //JPql select p from Pedido p where p.id = ?

    //SQL select * from pedido  where id = ?

    @Test
    public void buscarIdentificador() {
        TypedQuery<Pedido> pedidoQuery = entityManager
                .createQuery("select p from Pedido p where p.id=1", Pedido.class);
        Pedido pedido = pedidoQuery.getSingleResult();
        Assert.assertNotNull(pedido);
    }
}
