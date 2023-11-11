package com.nesrux.ecommerce.conhecendoOEntityManager;

import com.nesrux.ecommerce.model.produto.Produto;
import org.junit.Test;
import util.EntityManagerTest;

public class CacheDePrimeiroNivelTest extends EntityManagerTest {
    @Test
    public void veridicarCacheDePrimeiroNivel() {
        Produto produto = entityManager.find(Produto.class, 1);
        System.out.println(produto.getNome());
        System.out.println("-------------------");
        /*Ao invés do entity manager fazer uma segunda consulta SQL para
         * pegar esse dados, ele salva na memória local, então quando essa
         * mesma entidade é chamada denovo, ele nao realiza uma segunda consulta
         * fazendo ele ser mais eficiente*/
        Produto prodResgatado = entityManager.find(Produto.class, produto.getId());
        System.out.println(produto.getNome());
    }
}
