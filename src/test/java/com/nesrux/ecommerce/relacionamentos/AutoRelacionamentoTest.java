package com.nesrux.ecommerce.relacionamentos;

import com.nesrux.ecommerce.model.produto.Categoria;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

public class AutoRelacionamentoTest extends EntityManagerTest {
    @Test
    public void verificarAutoRelacionamento() {
        Categoria categoria = new Categoria();
        categoria.setNome("Eletronicos");

        Categoria categoriaFilha = new Categoria();
        categoriaFilha.setNome("Telefones moveis");
        categoriaFilha.setCategoriaPai(categoria);

        categoria.getCategorias().add(categoriaFilha);

        entityManager.getTransaction().begin();

        entityManager.persist(categoria);
        entityManager.persist(categoriaFilha);

        entityManager.getTransaction().commit();
        entityManager.clear();
        Categoria categoriaPaiPersistida = entityManager.find(Categoria.class,
                categoria.getId());

        Categoria categoriaFilhaPersistida = entityManager.find(Categoria.class,
                categoriaFilha.getId());

        Assert.assertNotNull(categoriaPaiPersistida);

        Assert.assertNotNull(categoriaFilhaPersistida);

        Assert.assertNotNull(categoriaFilhaPersistida.getCategoriaPai());

        Assert.assertTrue(categoriaPaiPersistida.getCategorias().stream()
                .findFirst().isPresent());
    }
}
