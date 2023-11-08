package com.nesrux.ecommerce.mapeamentoAvancado;

import com.nesrux.ecommerce.model.Produto;
import org.junit.Test;
import util.EntityManagerTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class DetalhesColumnTest extends EntityManagerTest {
    @Test
    public void ImpedirInsersaoDaColunaAtualizacao() {
        Produto produto = new Produto();
        produto.setNome("Teste atualizacao automatica");
        produto.setDescricao("teste da classe detalhesColumn");
        produto.setPreco(BigDecimal.ZERO);

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoPersist = entityManager.find(Produto.class, produto.getId());
        assertNotNull(produtoPersist.getDataCriacao());
        assertNull(produtoPersist.getDataUltimaAtualizacao());
    }

    @Test
    public void impedirAtualizacaoColunaCriacao() {
        entityManager.getTransaction().begin();
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setDataCriacao(LocalDateTime.now());

        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoPersist = entityManager.find(Produto.class, produto.getId());
        assertNotEquals(produto.getDataCriacao(), produtoPersist.getDataCriacao());
        assertEquals(produto.getDataUltimaAtualizacao(), produtoPersist.getDataUltimaAtualizacao());

    }
}

