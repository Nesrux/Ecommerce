package com.nesrux.ecommerce.mapeamentoAvancado;

import com.nesrux.ecommerce.model.produto.Produto;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import java.util.Arrays;

public class ElementCollectionTest extends EntityManagerTest {

    @Test
    public void aplicarTags() {
        entityManager.getTransaction().begin();
        Produto produto = entityManager.find(Produto.class, 1);
        produto.getTags().addAll(Arrays.asList("Ebook", "Livro-Digital",
                "Tecnologia", "Leitura"));
        entityManager.getTransaction().commit();
        entityManager.clear();


        Produto produtoVerificacao = entityManager.find(Produto.class, 1);
        produtoVerificacao.getTags().forEach(System.out::println);
        Assert.assertFalse(produtoVerificacao.getTags().isEmpty());
        /*ElementCollections é um tipo especial de anotação, que gerencia
         * os valores padroões do java, como Integer, BigDecimal, String etc
         * funciona que nem quando são as propriedades de valores unicos utilizando
         * esses objetos, como String nome, Bigdecimal valor etc, nao precisa fazer, nenhum
         * join collumn diferente, pois o JPA sabe lodar com esse tipo de dados */
    }
}
