package com.nesrux.ecommerce.criteria;

import com.nesrux.ecommerce.model.Pedido.Pedido;
import com.nesrux.ecommerce.model.produto.Produto;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

public class BasicoCriteriaApiTest extends EntityManagerTest {
    @Test
    public void projetarResultado() {
        CriteriaQuery<Object[]> query = entityManager.getCriteriaBuilder()
                .createQuery(Object[].class);

        Root<Produto> root = query.from(Produto.class);
        query.multiselect(root.get("id"), root.get("nome"));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(query);
        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr-> System.out.println(arr[0] + " -- " + arr[1]));
    }

    @Test
    public void retornarTodosOsProdutos() {
        // CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> query = entityManager.getCriteriaBuilder()
                .createQuery(Produto.class);

        Root<Produto> root = query.from(Produto.class);
        query.select(root);

        TypedQuery<Produto> typedQuery = entityManager.createQuery(query);
        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void selecionarAtributoParaRetorno() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<BigDecimal> criteriaQuery = criteriaBuilder.createQuery(BigDecimal.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root.get("total"))
                .where(criteriaBuilder.equal(root.get("id"), 1));

        TypedQuery<BigDecimal> typedQuery = entityManager.createQuery(criteriaQuery);
        BigDecimal valor = typedQuery.getSingleResult();
        Assert.assertEquals(new BigDecimal("2398.00"), valor);
    }


    @Test
    public void buscarPorIdentificador() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("id"), 1));

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Pedido> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }
}
