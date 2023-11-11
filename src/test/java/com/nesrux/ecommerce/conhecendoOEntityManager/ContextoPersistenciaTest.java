package com.nesrux.ecommerce.conhecendoOEntityManager;

import com.nesrux.ecommerce.model.produto.Produto;
import org.junit.Test;
import util.EntityManagerTest;

import java.math.BigDecimal;

public class ContextoPersistenciaTest extends EntityManagerTest {
    @Test
    public void contextoPersistencia() {
        Produto produto = entityManager.find(Produto.class, 1);
       /*Nesse método, o JPA faz um  dirtCheck para verificar se o objeto foi
       * modificado, se sim ele da um update no banco de dados*/
        produto.setPreco(new BigDecimal("150.50"));

        entityManager.getTransaction().begin();

        entityManager.getTransaction().commit();
        /*Os métodos de persist, find e marge colocam esse obj no contexto de persistencia
         * nesse contexto qualquer alteração feita no objeto quando ocorrer uma transação
         * ele vai fazer o update no banco de dados onde referencia esse objeto*/
    }
}
