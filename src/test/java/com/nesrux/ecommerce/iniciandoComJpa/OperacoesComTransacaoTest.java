package com.nesrux.ecommerce.iniciandoComJpa;

import com.nesrux.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import java.math.BigDecimal;

//@FixMethodOrder(MethodSorters.JVM)
public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void inserirObjetoComMerge() {
        Produto produto = new Produto();
        produto.setId(3);
        produto.setNome("Cbum");
        produto.setDescricao("Quem você pensa que é ?");
        produto.setPreco(new BigDecimal("5000"));

        entityManager.getTransaction().begin();
        /*Caso nao exista essa entidade no banco de dados,
         * o método merge cria uma nova instancia, ou seja é uma
         * querry a mais, caso ele ja exista ele só atualiza*/
        entityManager.merge(produto);

        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoInserido = entityManager.find(Produto.class, 3);
        System.out.println(produtoInserido);
        Assert.assertNotNull(produtoInserido);
    }

    @Test
    public void atualizatObjGerenciado() {
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        produto.setDescricao("A vida é trem bala, e a proxima parada é a cardiaca");

        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoBaseDeDados = entityManager.find(Produto.class, 1);
        Assert.assertEquals(produtoBaseDeDados.getDescricao(), produto.getDescricao());
    }


    @Test
    public void atualizatObj() {
        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("Cleiton");
        produto.setPreco(new BigDecimal("1234"));
        produto.setDescricao("Uma descricao qualquer");
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
        produto.setNome("Câmera");
        produto.setDescricao("Camera pika");
        produto.setPreco(new BigDecimal("5000"));

        entityManager.getTransaction().begin();

        /*persist Salva essa entidade no banco de dados*/
        entityManager.persist(produto);

        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoInserido = entityManager.find(Produto.class, produto.getId());

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
        Produto produto = new Produto();
        produto.setNome("Renatão de 4");
        produto.setDescricao("Um delicioso hamburguer feito de vacas dos alpes suiços");
        produto.setPreco(BigDecimal.ZERO);

        //Transação de criação
        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoPersistido = entityManager.find(Produto.class,
                produto.getId());

        //Transação de remoção
        entityManager.getTransaction().begin();
        /*Remove deleta essa entidade do banco de dados*/
        entityManager.remove(produtoPersistido);
        entityManager.getTransaction().commit();

        Produto produtoNulo = entityManager.find(Produto.class, produto.getId());
        Assert.assertNull(produtoNulo);
    }


}
