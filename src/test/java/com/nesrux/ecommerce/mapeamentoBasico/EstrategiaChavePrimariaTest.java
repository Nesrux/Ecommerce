package com.nesrux.ecommerce.mapeamentoBasico;

import com.nesrux.ecommerce.model.Categoria;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

public class EstrategiaChavePrimariaTest extends EntityManagerTest {

    @Test
    public void testarEstrategiaAuto(){
        Categoria categoria = new Categoria();
        categoria.setNome("Eletronicos");

        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        Categoria categoriaPersistida = entityManager.find(Categoria.class, categoria.getId());

        Assert.assertNotNull(categoriaPersistida);
        System.out.println(categoriaPersistida.getId());
    }
}
