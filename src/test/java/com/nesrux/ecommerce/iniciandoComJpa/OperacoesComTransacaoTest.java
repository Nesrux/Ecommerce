package com.nesrux.ecommerce.iniciandoComJpa;

import com.nesrux.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import java.math.BigDecimal;

//@FixMethodOrder(MethodSorters.JVM)
public class OperacoesComTransacaoTest extends EntityManagerTest {
    @Test
    public void atualizatObj() {
        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("Cleiton");

        entityManager.getTransaction().begin();

        /*Merge atualiza a entidade*/
        entityManager.merge(produto);

        entityManager.getTransaction().commit();
        Produto produtoBaseDeDados = entityManager.find(Produto.class, 1);

        Assert.assertEquals(produtoBaseDeDados.getNome(), produto.getNome());
    }

    @Test
    public void inserirObjeto() {
        Produto produto = new Produto();
        produto.setId(2);
        produto.setNome("Câmera");
        produto.setDescricao("Camera pika");
        produto.setPreco(new BigDecimal("5000"));

        entityManager.getTransaction().begin();

        /*persist Salva essa entidade no banco de dados*/
        entityManager.persist(produto);

        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoInserido = entityManager.find(Produto.class, 2);
        Assert.assertNotNull(produtoInserido);
    }


    @Test
    public void abrirEFecharTransacao() {
        //Inicia a transacao
        entityManager.getTransaction().begin();
        //Fecha transaçãp
        entityManager.getTransaction().commit();
    }

    @Test
    public void removerObjeto() {
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();

        /*Remove deleta essa entidade do banco de dados*/
        entityManager.remove(produto);

        entityManager.getTransaction().commit();
        Produto produtoNulo = entityManager.find(Produto.class, 1);

        Assert.assertNull(produtoNulo);
    }


}
