package com.nesrux.ecommerce.relacionamentos;

import com.nesrux.ecommerce.model.Categoria;
import com.nesrux.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

public class ManyToManyTest extends EntityManagerTest {
    @Test
    public void verificarRelacionamento() {
        Produto produto = entityManager.find(Produto.class, 1);
        Categoria categoria = entityManager.find(Categoria.class, 1);

        entityManager.getTransaction().begin();
        produto.getCategorias().add(categoria);
//        categoria.getProdutos().add(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();
        Produto produtoVerify = entityManager.find(Produto.class, produto.getId());
        //Categoria categoriaverify = entityManager.find(Categoria.class, categoria.getId());
        Assert.assertFalse(produtoVerify.getCategorias().isEmpty());

    }
}
