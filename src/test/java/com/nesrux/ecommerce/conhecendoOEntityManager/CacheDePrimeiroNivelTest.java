package com.nesrux.ecommerce.conhecendoOEntityManager;

import com.nesrux.ecommerce.model.Produto;
import org.junit.Test;
import util.EntityManagerTest;

public class CacheDePrimeiroNivelTest extends EntityManagerTest {
    @Test
    public void veridicarCacheDePrimeiroNivel(){
        Produto produto = entityManager.find(Produto.class, 1);
        System.out.println(produto.getNome());
        System.out.println("-------------------");

        Produto prodResgatado = entityManager.find(Produto.class, produto.getId());
        System.out.println(produto.getNome());
    }
}
