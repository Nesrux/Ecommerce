package com.nesrux.ecommerce.iniciandoComJpa;

import com.nesrux.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

public class ConsultandoRegistroTest extends EntityManagerTest {

    @Test
    public void buscarIdentificador() {
        Produto produto = entityManager.find(Produto.class, 1L);
        Assert.assertNotNull(produto);
        Assert.assertEquals("Kindle", produto.getNome());
    }
    @Test
    public void atualizarReferencia(){
        Produto produto = entityManager.find(Produto.class, 1L);
        produto.setNome("Microfone");

        entityManager.refresh(produto);
        Assert.assertEquals("Kindle", produto.getNome());
    }
}
