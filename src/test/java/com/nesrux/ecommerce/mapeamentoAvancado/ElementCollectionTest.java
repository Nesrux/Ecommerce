package com.nesrux.ecommerce.mapeamentoAvancado;

import com.nesrux.ecommerce.model.cliente.Cliente;
import com.nesrux.ecommerce.model.produto.Atributo;
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

    @Test
    public void verificarMapeamentoDeColecoesEmbadded() {
        Produto produto = entityManager.find(Produto.class, 1);
        produto.getAtributos().add(new Atributo("Tamanho", "150 cm"));
        produto.getAtributos().add(new Atributo("Cor", "preta"));

        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVeridicacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertFalse(produtoVeridicacao.getAtributos().isEmpty());
    }

    @Test
    public void verificarMapeamentoMap() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        cliente.getContatos().put("celular", "98874562354");

        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertFalse(clienteVerificacao.getContatos().isEmpty());

    }
}
