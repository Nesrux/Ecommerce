package com.nesrux.ecommerce.conhecendoOEntityManager;

import com.nesrux.ecommerce.model.Produto;
import org.junit.Test;
import util.EntityManagerTest;

import java.math.BigDecimal;

public class ContextoPersistenciaTest extends EntityManagerTest {
   @Test
    public void contextoPersistencia(){
       Produto produto = entityManager.find(Produto.class, 1);
       produto.setPreco(new BigDecimal("150.50"));

       entityManager.getTransaction().begin();

       entityManager.getTransaction().commit();
   }
}
