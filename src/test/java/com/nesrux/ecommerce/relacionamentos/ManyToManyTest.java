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
        /*Isso só funciona pois, o Produto é o owner dessa relação, ou seja O jpa só salva os dados
         * se forem salvos pelo dado no owner, se eu tentasse salvar a partir da classe categoria, isso nao iria funcionar
         * e me retornaria uma exception*/
        entityManager.getTransaction().begin();
        produto.getCategorias().add(categoria);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerify = entityManager.find(Produto.class, produto.getId());
        Assert.assertFalse(produtoVerify.getCategorias().isEmpty());

    }
}
