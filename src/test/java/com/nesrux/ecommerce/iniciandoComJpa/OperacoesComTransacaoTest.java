package com.nesrux.ecommerce.iniciandoComJpa;

import com.nesrux.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void inserirObjeto() {
        Produto produto = new Produto();
        produto.setId(2L);
        produto.setNome("Câmera");
        produto.setDescricao("Camera pika");
        produto.setPreco(new BigDecimal("5000"));

        entityManager.getTransaction().begin();

        entityManager.persist(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoInserido = entityManager.find(Produto.class, 2L);
        Assert.assertNotNull(produtoInserido);


    }

    @Test
    public void abrirEFecharTransacao() {
        Produto produto = new Produto();
        //Inicia a transacao
        entityManager.getTransaction().begin();
        //Fecha transaçãp
        entityManager.getTransaction().commit();
    }
}
