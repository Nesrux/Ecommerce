package com.nesrux.ecommerce.iniciandoComJpa;

import com.nesrux.ecommerce.model.Produto;
import org.junit.Test;
import util.EntityManagerTest;

public class OperacoesComTransacaoTest extends EntityManagerTest {
    @Test
    public void abrirEFecharTransacao() {
        Produto produto = new Produto();
        //Inicia a transacao
        entityManager.getTransaction().begin();

        entityManager.persist(produto);
        entityManager.merge(produto);
        entityManager.remove(produto);

        //Fecha transaçãp
        entityManager.getTransaction().commit();
    }
}
